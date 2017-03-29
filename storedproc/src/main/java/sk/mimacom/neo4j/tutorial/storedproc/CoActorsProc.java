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

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CoActorsProc {

	private static final String ACTED_IN = "ACTED_IN";

	@Context
	public GraphDatabaseService db;

	@Context
	public Log log;

	public List<Long> findCoactors(long initialNodeId) {
		Node initialNode = db.getNodeById(initialNodeId);
		return Collections.singletonList(initialNodeId); //TODO: Implement
	}

	private Stream<Long> getActorIdsForMovieNode(Node movieNode, long excludeActorId) {
		Preconditions.checkArgument(movieNode.hasLabel(Label.label("Movie")), "Can only get actors for a Movie");

		Stream<Relationship> relationshipStream = StreamSupport.stream(
				movieNode.getRelationships(RelationshipType.withName(ACTED_IN),
						Direction.BOTH)
						.spliterator(), false
		);
		return Stream.empty(); //TODO: Implement
	}

}
