package sk.mimacom.neo4j.tutorial.javaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.mimacom.neo4j.tutorial.javaclient.entities.Actor;
import sk.mimacom.neo4j.tutorial.javaclient.service.ActorApplicationService;

@RestController
@RequestMapping("/rest/actors")
public class ActorsController {

	private final ActorApplicationService actorApplicationService;

	@Autowired
	public ActorsController(ActorApplicationService actorApplicationService) {
		this.actorApplicationService = actorApplicationService;
	}

	@RequestMapping(value = "/byName", method = RequestMethod.GET)
	public ResponseEntity<Actor> findActorByName(@RequestParam String name) {
		Actor actorByName = actorApplicationService.findActorByName(name);
		//TODO: Why is the "filmography" field null?
		return actorByName != null
				? new ResponseEntity<>(actorByName, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
