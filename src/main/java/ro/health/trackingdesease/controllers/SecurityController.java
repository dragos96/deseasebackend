package ro.health.trackingdesease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.health.trackingdesease.model.User;
import my.health.trackingdesease.security.config.UUserDetailsService;

@RestController
@RequestMapping("/security/users")
public class SecurityController {

	
//	@Autowired
//	private RepoUser repoUser;
//	
	@Autowired
	private UUserDetailsService userDetailsService;
	
	
	@PostMapping("/register")
	public User registerNewAccount(@RequestBody User user) {
		User userSaved = userDetailsService.registerNewUserAccount(user);
		return userSaved;
	}
}
