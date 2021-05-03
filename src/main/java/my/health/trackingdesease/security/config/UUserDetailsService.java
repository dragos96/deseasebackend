package my.health.trackingdesease.security.config;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.health.trackingdesease.dao.RepoUser;



@Service
public class UUserDetailsService implements UserDetailsService {

	
	@Autowired
	private RepoUser repoUser;



	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(username);
    }

    
    private UserDetails findUser(String username){
    	Optional<my.health.trackingdesease.model.User> optional = repoUser.findByEmail(username);
    	
    	if(optional.isPresent()){
    		my.health.trackingdesease.model.User theUser = optional.get();
    		
    		
    		
    		UserDetails springSecurityUser = User.withDefaultPasswordEncoder()
	                .username(theUser.getEmail())
	                .password(theUser.getPassword())
	                .authorities(getAuthority())
	                .build();
    	
		
			return springSecurityUser;
    	}

    	else{
    		throw new UsernameNotFoundException("");
    	}
    	
    }
    
   

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.emptyList();
    }
}