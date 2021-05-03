package my.health.trackingdesease.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.health.trackingdesease.model.Person;

@Repository
public interface RepoPerson extends CrudRepository<Person, Integer>{

	
}
