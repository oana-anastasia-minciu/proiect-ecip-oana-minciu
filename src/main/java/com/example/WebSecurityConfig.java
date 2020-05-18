package com.example;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled"
			+ " from users where username=?")
		.authoritiesByUsernameQuery("select username, authority "
			+ "from authorities where username=?")
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{

		http
			.authorizeRequests()
				.antMatchers("/").hasAnyRole("USER", "ADMIN")
				.antMatchers("/rating").permitAll()
				.antMatchers("/all").permitAll()
				.antMatchers("/hello").hasAnyRole("USER", "ADMIN")
				.antMatchers("/greeting").hasAnyRole("ADMIN")
				.antMatchers("/coursefeedback").hasAnyRole("USER")
				.antMatchers("/feedbackresults").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout().permitAll();


			// 	
		// 	.and()
		// .httpBasic();

		//http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
		//.and()
		//.httpBasic(); // Authenticate users with HTTP basic authentication
	}


/*
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();

		http.authorizeRequests()
				.antMatchers("/devs/*").hasAnyRole("boss", "dev")
				.antMatchers("/boss/*").hasRole("boss")
				.antMatchers("/").permitAll();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery(
				"SELECT username, password, enabled from users where username = ?")
			.authoritiesByUsernameQuery(
				"SELECT u.username, a.authority " +
				"FROM user_authorities a, users u " +
				"WHERE u.username = ? " +
				"AND u.id = a.user_id"
			);
	}
*/
	
/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	*/
	
	/*
	@Autowired
	private DataSource dataSource;

	//Enable jdbc authentication
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/hello").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/greeting").hasAnyRole("ROLE_ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout().permitAll();

		http.csrf().disable();
	}
	*/
}