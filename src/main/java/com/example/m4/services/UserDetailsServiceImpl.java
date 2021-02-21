package com.example.m4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.m1.dao.AccountDao;
import com.example.m1.dao.RoleDao;
import com.example.m1.model.Account;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
  AccountDao dao;
  RoleDao roleDao;
  
  @Autowired
  public UserDetailsServiceImpl(AccountDao dao, RoleDao roleDao) {
	this.dao = dao;
	this.roleDao = roleDao;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Account user = new Account();
	try {
		user = dao.findByUsername(username);
	}catch (EmptyResultDataAccessException e) {
		 throw new UsernameNotFoundException("User Not Found with username: " + username);
	}
    user.setRoles(roleDao.getRolesByUser(user.getUserId()));
    return UserDetailsImpl.build(user);
  }

}
