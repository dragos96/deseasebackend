package ro.health.trackingdesease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.health.trackingdesease.dao.RepoPerson;
import my.health.trackingdesease.dao.RepoUser;
import my.health.trackingdesease.model.Person;
import my.health.trackingdesease.model.User;

@RestController
@RequestMapping("/rest/persons")
public class PersonsController {

	@Autowired
	private RepoPerson repoPerson;
	
	@Autowired
	private RepoUser repoUser;
	
	@GetMapping("/all")
	public Iterable<Person> findAll(){
		return repoPerson.findAll();
	}
	
	@PostMapping("/save")
	public Person save(@RequestBody Person person) {
		Person savedPerson = repoPerson.save(person);
		return savedPerson;
	}
	
	@DeleteMapping("/del/{idPers}")
	public Person delPerson(@PathVariable("idPers") int idPers) {
		Person delP = repoPerson.findById(idPers).get();
		repoPerson.delete(delP);
		return delP;
	}
	
	@PutMapping("/update/{idPerson}/{parentType}/{idParent}")
	public Person updatePerson(@PathVariable("parentType") String parentType, @PathVariable("idParent") int idParent, @PathVariable("idPerson") int idPerson) {
		// Person savedPerson = repoPerson.save(person);
		
		// TODO: cannot self-assign
	    // TODO: cannot create circular dependency
		Person person = repoPerson.findById(idPerson).get();
		Person parent = repoPerson.findById(idParent).get();
		if(parentType.equals("mother")) {
			person.setMother(parent);
		}else if(parentType.equals("father")){
			person.setFather(parent);
		}else {
			// TODO: return response error
		}
		Person updatedPerson = repoPerson.save(person);
		return updatedPerson;
	}
	
	@PutMapping("/associate-with-user/{idPerson}/{idUser}")
	public Person updateAssociation(@PathVariable("idPerson") int idPerson, @PathVariable("idUser") int idUser) {
		Person person = repoPerson.findById(idPerson).get();
		User user = repoUser.findById(idUser).get();
		person.setUser(user);
		Person updatedPerson = repoPerson.save(person);
		return updatedPerson;
	}
}
