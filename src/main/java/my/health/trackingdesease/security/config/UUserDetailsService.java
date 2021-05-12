package my.health.trackingdesease.security.config;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import my.health.trackingdesease.dao.RepoUser;

@Service
public class UUserDetailsService implements UserDetailsService {

	@Autowired
	private RepoUser repoUser;

	@Autowired
	private PasswordEncoder passwordEncoder;

	

	public my.health.trackingdesease.model.User registerNewUserAccount(my.health.trackingdesease.model.User user)  {
//	    if (emailExist(accountDto.getEmail())) {
//	        throw new EmailExistsException(
//	          "There is an account with that email adress:" + accountDto.getEmail());
//	    }
		// my.health.trackingdesease.model.User user = new my.health.trackingdesease.model.User();
	    
	    System.out.println("REGISTERING: " + user);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    
	    return repoUser.save(user);
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findUser(username);
	}

	// login
	private UserDetails findUser(String username) {
		Optional<my.health.trackingdesease.model.User> optional = repoUser.findByEmail(username);

		if (optional.isPresent()) {
			my.health.trackingdesease.model.User theUser = optional.get();

			System.out.println("LOGGIN IN: " + theUser.getPassword());
			System.out.println("LOGGIN IN: " + (theUser.getPassword())); // this.passwordEncoder.encode
			// TODO: ????
			UserDetails springSecurityUser = User.builder().username(theUser.getEmail())
					.password(theUser.getPassword()).authorities(getAuthority()).build();

			return springSecurityUser;
		}

		else {
			throw new UsernameNotFoundException("");
		}

	}

//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Collections.emptyList();
	}
}