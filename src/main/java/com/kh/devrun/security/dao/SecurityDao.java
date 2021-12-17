package com.kh.devrun.security.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityDao {

	UserDetails loadUserByUsername(String username);

	int insertAuthorities(int memberNo);

}
