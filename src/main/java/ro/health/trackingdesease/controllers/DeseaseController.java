package ro.health.trackingdesease.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.health.trackingdesease.dao.RepoDesease;
import my.health.trackingdesease.dao.RepoUser;
import my.health.trackingdesease.model.Desease;
import my.health.trackingdesease.model.User;
import my.health.trackingdesease.util.IConstants;
import my.health.trackingdesease.util.UserHelper;

@RestController
@RequestMapping("/rest/desease")
public class DeseaseController {

	
	@Autowired
	private RepoDesease repoDesease;
	
	@Autowired
	private RepoUser repoUser;
	
	@Autowired
	private UserHelper userHelper;
	
	
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<Desease>> findAll(Principal principal){
		
		Optional<User> opUser = userHelper.getUserFromPrincipal(principal);
		if(!opUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		User user = opUser.get();
		if(!user.getUserType().equals(IConstants.USER_TYPE_SPECIALIST)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.ok(repoDesease.findAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<Desease> saveDesease(@RequestBody Desease desease, Principal principal){
		Optional<User> opUser = userHelper.getUserFromPrincipal(principal);
		if(!opUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		User user = opUser.get();
		if(!user.getUserType().equals(IConstants.USER_TYPE_SPECIALIST)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		Desease deseaseSaved = repoDesease.save(desease);
		return ResponseEntity.ok(deseaseSaved);
		
	}
}
