package com.suresh.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.StateEntity;

public interface StatesRepository extends JpaRepository<StateEntity,Serializable> {

	public List<StateEntity> findByCountryId(Integer countryId);
}
