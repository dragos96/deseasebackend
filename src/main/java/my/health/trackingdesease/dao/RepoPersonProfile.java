package my.health.trackingdesease.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.health.trackingdesease.model.PersonProfile;

@Repository
public interface RepoPersonProfile extends CrudRepository<PersonProfile, Integer>{

	
}
