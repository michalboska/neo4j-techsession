package sk.mimacom.neo4j.tutorial.javaclient.entities;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label = "Person")
public class Actor {

	@GraphId
	private Long id;

	@Property(name = "name")
	private String fullName;

	@Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
	private List<Movie> filmography;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Movie> getFilmography() {
		return filmography;
	}

	public void setFilmography(List<Movie> filmography) {
		this.filmography = filmography;
	}
}
