package sk.mimacom.neo4j.tutorial.javaclient.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sk.mimacom.neo4j.tutorial.javaclient.Application;
import sk.mimacom.neo4j.tutorial.javaclient.BaseSpringDataNeo4jTest;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public class ActorRepositoryTest extends BaseSpringDataNeo4jTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActorRepositoryTest.class);

	@Autowired
	private ActorRepository actorRepository;

	@Test
	public void testFindActorsByFullName() {
		long actorsTotal = actorRepository.count();
		assertThat(actorsTotal).isGreaterThan(0);
		LOGGER.info("Found total {} actors", actorsTotal);
		Actor tomHanks = actorRepository.findActorByName("Tom Hanks");
		assertThat(tomHanks).isNotNull();
		assertThat(tomHanks.getFullName()).isEqualTo("Tom Hanks");

		Actor tomCruise = actorRepository.findActorByName("Tom Cruise");
		assertThat(tomCruise).isNotNull();
		assertThat(tomCruise.getFullName()).isEqualTo("Tom Cruise");

		Actor keanuReeves = actorRepository.findActorByName("Keanu Reeves");
		assertThat(keanuReeves).isNotNull();
		assertThat(keanuReeves.getFullName()).isEqualTo("Keanu Reeves");

	}

}