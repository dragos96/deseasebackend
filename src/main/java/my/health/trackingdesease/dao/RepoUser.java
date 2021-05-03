package my.health.trackingdesease.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.health.trackingdesease.model.User;

@Repository
public interface RepoUser extends CrudRepository<User, Integer>{

	
	public Optional<my.health.trackingdesease.model.User> findByEmail(String email);
	
}
