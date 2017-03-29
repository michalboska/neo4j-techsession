package sk.mimacom.neo4j.tutorial.javaclient.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;

public interface ActorRepository extends Neo4jRepository<Actor, Long> {

	@Query("match (actor: Person) where actor.name={name} return actor")
	Actor findActorByName(@Param("name") String name);
}
