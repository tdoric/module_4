package com.example.m4.services;

import org.springframework.beans.factory.annotation.Autowired;
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
 
  @Autowired
  AccountDao dao;
  @Autowired
  RoleDao roleDao;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = dao.findByUsername(username);
    user.setRoles(roleDao.getRolesByUser(user.getUserId()));
    if(user==null) {
    	 throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
    
    return UserDetailsImpl.build(user);
  }

}
