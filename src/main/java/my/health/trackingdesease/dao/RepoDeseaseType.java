package my.health.trackingdesease.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.health.trackingdesease.model.DeseaseType;

@Repository
public interface RepoDeseaseType extends CrudRepository<DeseaseType, Integer>{

	
}
