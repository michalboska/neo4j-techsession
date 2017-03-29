package sk.mimacom.neo4j.tutorial.storedproc;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.harness.junit.Neo4jRule;

@RunWith(BlockJUnit4ClassRunner.class)
public class CoActorsProcTest {

	private static final String BOLT_URI = "bolt://localhost:7687";

	private Transaction transaction;
	private Session session;
	private Driver driver;

	@Rule
	public Neo4jRule neo4jRule = new Neo4jRule().withProcedure(CoActorsProc.class);

	@Before
	public void setUp() {
		driver = GraphDatabase.driver(BOLT_URI);
		session = driver.session();
		transaction = session.beginTransaction();
	}

	@Test
	public void testProcedureFindsCoactors() {
		throw new RuntimeException("asdasd");
	}

	@After
	public void tearDown() {
		transaction.failure();
		transaction.close();
		driver.close();
	}


}