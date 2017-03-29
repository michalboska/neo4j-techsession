package sk.mimacom.neo4j.tutorial.javaclient.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;

import java.util.List;

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

	@Query("match (actor: Person) where actor.name={name} return actor")
	Actor findActorByName(@Param("name") String name);

	@Query("MATCH (actor: Person)-[:ACTED_IN]-(:Movie)-[:ACTED_IN]-(coactor) WHERE actor.name={name} RETURN DISTINCT(coactor)")
	List<Actor> findImmediateCoactorsByName(@Param("name") String name);

	@Query("MATCH (n:Person)-[e:ACTED_IN]-(m:Movie)-[e2:ACTED_IN]-(c:Person) " +
			"WHERE n.name={name} " +
			"WITH n,count(e) AS incidence,collect(m.title) AS movies,c AS coactor " +
			"ORDER BY incidence DESC LIMIT 1 " +
			"RETURN coactor")
	Actor findMostFrequentCoactor(@Param("name") String name);
}
