package com.pic.share.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pic.share.dao.IUserAppRepository;
import com.pic.share.model.UserApp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final IUserAppRepository userAppRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user = userAppRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found With Username: "+ username);
		}
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
		});
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
