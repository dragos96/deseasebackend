package ro.health.trackingdesease.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.health.trackingdesease.dao.RepoUser;
import my.health.trackingdesease.model.User;

@RestController
@RequestMapping("/rest/users")
public class UserController {

	
	@Autowired
	private RepoUser repoUser;
	
	@GetMapping("/hello")
	public Map<String, String> test(){
		Map<String, String> result = new HashMap<>();
		result.put("status", "ok");
		return result;
	}
	
	@GetMapping("/all")
	public Iterable<User> findAll(){
		return repoUser.findAll();
	}
	
}
