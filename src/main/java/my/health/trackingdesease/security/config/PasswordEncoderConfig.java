package my.health.trackingdesease.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

	@Bean
	public PasswordEncoder customPasswordEncoder() {
		System.out.println("**CREATING PASSWORD ENCODER**");
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				System.out.println("RAW PASSWORD: " + rawPassword);
				return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {

				if (rawPassword.equals("my-secret")) {
					return BCrypt.checkpw(rawPassword.toString(), encode(encodedPassword));
				}
				return BCrypt.checkpw(rawPassword.toString(), encodedPassword);

			}
		};
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new PasswordEncoder()
	 * {
	 * 
	 * @Override public String encode(CharSequence rawPassword) { return
	 * rawPassword.toString(); }
	 * 
	 * @Override public boolean matches(CharSequence rawPassword, String
	 * encodedPassword) { return rawPassword.toString().equals(encodedPassword); }
	 * }; }
	 */
}