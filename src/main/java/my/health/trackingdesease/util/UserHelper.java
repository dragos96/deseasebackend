package my.health.trackingdesease.util;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.health.trackingdesease.dao.RepoUser;
import my.health.trackingdesease.model.User;

@Service
public class UserHelper {

	@Autowired
	private RepoUser repoUser;
	
	public Optional<User> getUserFromPrincipal(Principal principal) {
		Optional<User> opUser = repoUser.findByEmail(principal.getName());
		return opUser;
	}
	
}
