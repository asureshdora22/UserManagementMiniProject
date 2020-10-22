package com.suresh.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.UserAccountEntity;



public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {

	public UserAccountEntity findByUserEmail(String email);
	public UserAccountEntity findByUserEmailAndUserPwd(String email,String password);
}
