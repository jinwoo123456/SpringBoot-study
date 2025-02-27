package com.edu.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

/*
 * 스프링 컨테이너가 시작될때 빈이 생성되어야 하므로 기본패키지 하위에
 * 정의한 후 어노테이션을 부착한다. 스프링 시큐리티 사용을 위한 설정
 * 클래스로 정의한다.
 * */
@Configuration
public class WebSecurityConfig {
	
	/*2단계(디자인 커스텀)에서 인메모리 방식으로 사용했던 메서드는 
	 * 이번 3단게에서는사용하지 않으니 삭세처리 */
	
	//db연결을 위한 datasource를 자동주입 받는다.
	@Autowired
	private DataSource dataSource;
	
	/*2개의 쿼리문 실행을 통해 사용자의 이늦ㅇ정보와 권환을 인출한다.
	 * 첫번쨰 쿼리는 사용자의 아이디.비번, 그리고 계정활성화 여부를 확인한다.*/
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication()
	        .dataSource(dataSource)
	        .usersByUsernameQuery(
	            "select user_id, user_pw, enabled from security_admin where user_id = ?"
	        )
	        .authoritiesByUsernameQuery(
	            "select user_id, authority from security_admin where user_id = ?"
	        )
	        .passwordEncoder(new BCryptPasswordEncoder());
	}

	/*인증실패에 대한 핸들러를 제작했다면 사용을 위해 빈을 자동 주입받는다.
	 * 그리고 시큐리티 서렂ㅇ 부분의 failureHandler() 메서드를 추가한다.*/
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
	throws Exception {
		http.csrf((csrf)-> csrf.disable())
		.cors((cors)-> cors.disable())
		.authorizeHttpRequests((request)->request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/css/**","/js/**","/images/**").permitAll()
				.requestMatchers("/member/**").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated()
				);
		/*
		 * 로그인 페이지에 대한 디자인 커스터마이징 설정.
		 * loginPage : 로그인 페이지의 요청명
		 * loginProcessingUrl :폼값을 전송하여 로그인 처리할 요청명
		 * failureUrl : 로그인에 실패한 경우 이동할 요청명
		 * failureHandler :  별도의 핸들러 빈을 등록 후 에러처리
		 * usernameParameter : 아이디 입력을 위한 <input>의 name속성값
		 * passwordParameter : 패스워드 입력을 위한 name 속성값
		 * 
		 * */
		http.formLogin((formLogin)->
			formLogin.loginPage("/myLogin.do")//default : /login
			.loginProcessingUrl("/myLoginAction.do")
			.failureUrl("/myError.do")
			//.failureHandler(myAuthFailureHandler
			.failureHandler(myAuthFailureHandler)
			.usernameParameter("my_id")
			.passwordParameter("my_pass")
			.permitAll());
		/*
		 * 로그아웃에 대한 커스터마이징
		 * logoutUrl : 로그아웃을 위한 요청명
		 * logoutSuccessUrl : 로그아웃 이후 이동할 페이지
		 * */
		http.logout((logout)-> logout
			.logoutUrl("/myLogout.do") // deafault : /logout
			.logoutSuccessUrl("/")
			.permitAll());
		/*
		 * 권한이 부족할 경우 이동할 위치 지정. 가령 user로 로그인 했는데
		 * admin 권한이 필요한 페이지에 접근하는 경우 지정된 페이지로 이동하게 된다.
		 * */
		http.exceptionHandling((expHandling)->expHandling
				.accessDeniedPage("/denied.do"));
		return http.build();
	}
	
	
	
	/*
	 * @Bean public UserDetailsService users() { UserDetails user = User.builder()
	 * .username("user") .password(passwordEncoder().encode("1234")) .roles("USER")
	 * // ROLEUSER 에서 ROLE은 자동으로 붙는다. .build(); UserDetails admin = User.builder()
	 * .username("admin") .password(passwordEncoder().encode("1234"))
	 * .roles("USER","ADMIN") .build();
	 * 
	 * //메모리에 잠깐 입력해두는 구조 return new InMemoryUserDetailsManager(user,admin); }
	 */
	/*패스워드 인코더 : 패스워드를 저장하기 전 암호화한다.*/
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();	
//	}
	
	
}
