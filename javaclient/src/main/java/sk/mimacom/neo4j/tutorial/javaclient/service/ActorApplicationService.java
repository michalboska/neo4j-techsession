package sk.mimacom.neo4j.tutorial.javaclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;
import sk.mimacom.neo4j.tutorial.javaclient.repository.ActorRepository;

@Service
@Transactional(readOnly = true)
public class ActorApplicationService {

	private final ActorRepository actorRepository;

	@Autowired
	public ActorApplicationService(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	public Actor findActorByName(String name) {
		return actorRepository.findActorByName(name);
	}

}
