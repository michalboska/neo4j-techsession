package sk.mimacom.neo4j.tutorial.storedproc;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;
import org.neo4j.harness.junit.Neo4jRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(BlockJUnit4ClassRunner.class)
public class CoActorsProcTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoActorsProcTest.class);

	private static final String BOLT_URI = "bolt://localhost:7687";

	private Transaction transaction;
	private Session session;
	private Driver driver;

	@Rule
	public Neo4jRule neo4jRule = new Neo4jRule().withProcedure(CoActorsProc.class);

	@Before
	public void setUp() {
		driver = GraphDatabase.driver(neo4jRule.boltURI());
		session = driver.session();
		transaction = session.beginTransaction();

		LOGGER.info("Truncating the database");
		transaction.run("MATCH (n) DETACH DELETE n");

		//load the sample cypher
		StringBuffer stringBuffer = new StringBuffer();
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("init-data.cypher");
		     InputStreamReader reader = new InputStreamReader(stream);
		     BufferedReader br = new BufferedReader(reader)) {
			br.lines().forEach(line -> stringBuffer.append(line).append("\n"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		transaction.run(stringBuffer.toString());
	}

	@Test
	public void testProcedureFindsCoactors() {
		long tomCruiseId = transaction.run("match (n:Person {name: 'Tom Cruise'}) return ID(n)")
				.single()
				.get(0)
				.asLong();
		StatementResult result = transaction.run("CALL tutorial.findCoactors({id})",
				Values.parameters("id", tomCruiseId));
		List<Value> nodeIds = result.list()
				.stream()
				.map(item -> item.get("nodeId"))
				.collect(Collectors.toList());
		Assertions.assertThat(nodeIds).isNotEmpty();

	}

	@After
	public void tearDown() {
		transaction.failure();
		transaction.close();
		driver.close();
	}


}