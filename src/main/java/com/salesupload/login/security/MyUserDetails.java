package com.salesupload.login.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesupload.usermaster.enitity.Usermaster;

public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 4655529215541993969L;
	
	
	Usermaster usermaster;
	public Usermaster getUsermaster() {
		return usermaster;
	}

	public void setUsermaster(Usermaster usermaster) {
		this.usermaster = usermaster;
	}

	Collection<GrantedAuthority> grantedAuthorities;
	
	
	public MyUserDetails(Usermaster usermaster, Collection<GrantedAuthority> grantedAuthorities) {
		this.usermaster = usermaster;
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(usermaster.getPassword());
	}

	@Override
	public String getUsername() {
		return usermaster.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return (usermaster.getActive() == 1) ? true : false;
	}

}
