package com.PayMyBuddy.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.PayMyBuddy.constants.AccountType;
import com.PayMyBuddy.model.User;
import com.PayMyBuddy.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		if (userEmail.trim().isEmpty()) {
			throw new UsernameNotFoundException("usermail is empty");
		}
		
		User user = userService.getUserByEmail(userEmail).get();
		
		if (user == null) {
			throw new UsernameNotFoundException("User " + userEmail + " not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getGrantedAuthorities(user));
	
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		String userMail = user.getEmail();
		String role = AccountType.USER.toString();
		if (userMail == "admin@mail.com") {
			role = AccountType.SYSTEM.toString();
		} else {
			role = AccountType.USER.toString();
		}
		
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}
	
}
