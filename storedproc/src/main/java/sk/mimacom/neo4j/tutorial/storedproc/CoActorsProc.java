package sk.mimacom.neo4j.tutorial.storedproc;

import com.google.common.base.Preconditions;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Mode;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CoActorsProc {

	private static final String ACTED_IN = "ACTED_IN";

	@Context
	public GraphDatabaseService db;

	@Context
	public Log log;

	@Procedure(name = "tutorial.findCoactors", mode = Mode.READ)
	public Stream<SearchHit> findCoactors(@Name("initialId") long initialNodeId) {
		Node initialNode = db.getNodeById(initialNodeId);
		Preconditions.checkArgument(initialNode.hasLabel(Label.label("Person")), "Can only find coactors of a Person");
		Stream<Relationship> actedInRelationships = StreamSupport.stream(
				initialNode.getRelationships(RelationshipType.withName(ACTED_IN),
						Direction.BOTH).spliterator()
				, false);
		return actedInRelationships
				.flatMap(relationship -> {
					Node movieNode = relationship.getOtherNode(initialNode);
					if (!movieNode.hasLabel(Label.label("Movie"))) {
						//don't include this as it's not a movie
						return Stream.empty();
					}
					return getActorIdsForMovieNode(movieNode, initialNodeId);
				})
				.map(SearchHit::new);
	}

	private Stream<Long> getActorIdsForMovieNode(Node movieNode, long excludeActorId) {
		Preconditions.checkArgument(movieNode.hasLabel(Label.label("Movie")), "Can only get actors for a Movie");

		Stream<Relationship> relationshipStream = StreamSupport.stream(
				movieNode.getRelationships(RelationshipType.withName(ACTED_IN),
						Direction.BOTH)
						.spliterator(), false
		);
		return relationshipStream
				.flatMap(relationship -> {
					Node actorNode = relationship.getOtherNode(movieNode);
					return actorNode.getId() == excludeActorId
							? Stream.empty()
							: Stream.of(actorNode.getId());
				});
	}

	public static class SearchHit {
		public Long nodeId;

		public SearchHit(Long nodeId) {
			this.nodeId = nodeId;
		}
	}

}
