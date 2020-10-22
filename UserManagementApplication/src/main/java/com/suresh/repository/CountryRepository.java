package com.suresh.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

}
