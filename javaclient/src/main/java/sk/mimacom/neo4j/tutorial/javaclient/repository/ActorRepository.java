package sk.mimacom.neo4j.tutorial.javaclient.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;

import java.util.List;

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

	@Query("MATCH (actor: Person) RETURN actor")
		//TODO: Finish
	Actor findActorByName(@Param("name") String name);

	@Query("MATCH (actor: Person) RETURN actor")
		//TODO: Finish
	List<Actor> findImmediateCoactorsByName(@Param("name") String name);

	@Query("MATCH (n:Person) RETURN n")
		//TODO: Finish
	Actor findMostFrequentCoactor(@Param("name") String name);
}
