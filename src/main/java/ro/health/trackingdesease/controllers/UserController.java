package ro.health.trackingdesease.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.health.trackingdesease.dao.RepoUser;
import my.health.trackingdesease.model.User;
import my.health.trackingdesease.util.IConstants;
import my.health.trackingdesease.util.UserHelper;

@RestController
@RequestMapping("/rest/users")
public class UserController {

	
	@Autowired
	private RepoUser repoUser;
	
	@Autowired
	private UserHelper userHelper;
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<User>> findAll(Principal principal){
		Optional<User> opUserLoggedIn = userHelper.getUserFromPrincipal(principal);
		if(!opUserLoggedIn.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		User userLoggedIn = opUserLoggedIn.get();
		if(!userLoggedIn.getUserType().equals(IConstants.USER_TYPE_ADMIN)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.ok(repoUser.findAll());
	}
	
	
	@PostMapping("/save-specialist/{user-type-to-be-saved}")
	public ResponseEntity<User> saveSpecialistUser(@RequestBody User user, Principal principal, @PathVariable("user-type-to-be-saved") String userType){
	
		
		String userTypeLoggedIn = null;
		String userTypeToSave = null;
		switch(userType) {
		case IConstants.USER_TYPE_SPECIALIST:
			userTypeLoggedIn = IConstants.USER_TYPE_ADMIN;
			userTypeToSave = IConstants.USER_TYPE_SPECIALIST;
			break;
		case IConstants.USER_TYPE_USER:
			userTypeLoggedIn = IConstants.USER_TYPE_SPECIALIST;
			userTypeToSave = IConstants.USER_TYPE_USER;
			break;
			default:
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		Optional<User> opUserLoggedIn = userHelper.getUserFromPrincipal(principal);
		if(!opUserLoggedIn.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		User userLoggedIn = opUserLoggedIn.get();
		if(!userLoggedIn.getUserType().equals(userTypeLoggedIn)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		
		user.setUserType(userTypeToSave);
		User userSaved = repoUser.save(user);
		return ResponseEntity.ok(userSaved);
	}
	

}
