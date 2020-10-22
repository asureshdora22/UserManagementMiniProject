package com.suresh.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.CitiesEntity;

public interface CitiesRepository extends JpaRepository<CitiesEntity, Serializable> {

	public List<CitiesEntity> findByStateId(Integer stateId);
}
