package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // SA OVIM OMOGUCAVAM CITANJE KORISNIKA IZ BAZE PODATAKA, PRI CEMU SAM KORISTIO DEFAULT-NE TABELE ZA JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        // PODESAVAM DA SE KORISTE CUSTOM TABELE U BAZI PODATAKA
        // MORA DA NAPRAVIM QUERY ZA DOBIJANJE KORISNIKA I ULOGE

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

                                                // POSLE UPITNIKA CE SE PROSLEDITI userId VEZAN ZA KORISNIKA KOJI SE ULOGOVAO PREKO FORME
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

                                                // TAKODJE, POSLE UPITNIKA CE SE NALAZITI userId VEZAN ZA KORISNIKA ULOGOVANOG PREKO FORME
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;

        //(OVO JE BILO PRETHODNO, PRE DODAVANJA KODA ZA CUSTOM TABELE)  return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        // SA OVIM SE OBEZBEDJUJU LINKOVI ZA ODREDJENE ULOGE
                        // SA ** SE UKLJUCUJU SVI PODLINKOVI
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated() //svaki zahtev mora da se autentifikuje, mora da budes ulogovan
                )
                .formLogin(form->
                form
                        .loginPage("/showMyLoginPage")

                        //za ovaj link ne mora da se pise kod u controller-u
                        .loginProcessingUrl("/authenticateTheUser") //sa moje custom forme ce se podaci proslediti na ovaj link gde ce se proveravati podaci
                        .permitAll()//svima ce se pokazati login forma, ne mora da se bude ulogovan
                )
                //dodaje logout support koji omogucava default-ni url /logout
                .logout(
                        logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer
                                //korisnik ce da se preusmeri na ovaj link ako dodje do greske vezane za autentifikaciju, ili ako korisnik ne moze da udje na odredjeni link
                                .accessDeniedPage("/access-denied"));

        return http.build();
    }
}


//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails rose = User.builder()
//                .username("rose")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, rose);
//    }