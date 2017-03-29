package sk.mimacom.neo4j.tutorial.javaclient;

import org.junit.Before;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public abstract class BaseSpringDataNeo4jTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseSpringDataNeo4jTest.class);

	@Autowired
	private Session session;

	@Before
	public void setUp() {
		LOGGER.info("Truncating the database");
		session.query("MATCH (n) DETACH DELETE n", Collections.emptyMap());

		//load the sample cypher
		StringBuffer stringBuffer = new StringBuffer();
		try (InputStream stream = getClass().getClassLoader().getResourceAsStream("init-data.cypher");
		     InputStreamReader reader = new InputStreamReader(stream);
		     BufferedReader br = new BufferedReader(reader)) {
			br.lines().forEach(line -> stringBuffer.append(line).append("\n"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		session.query(stringBuffer.toString(), Collections.emptyMap());
	}

}
