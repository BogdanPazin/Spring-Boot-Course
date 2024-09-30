package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

    /*
        POSTO SAM OVDE NAPRAVIO KORISNIKE, SPRING BOOT NECE DA KORISTI DEFAULT-NE PODATKE
        ZA LOGOVANJE IZ APPLICATION.PROPERTIES VEC CE DA KORISTI PODATKE ODAVDE
     */



    // omogucavam konfiguraciji da koristi jdbc, tako da se poveze sa bazom
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // sam datasource je generisao spring boot

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        /*
        OVAJ KOD JE POTREBAN JER TAKO UPUCUJEM SPRING SECURITY-JU KAKO DA NALAZI USER-E
        I KAKO DA NALAZI ODGOVARAJUCI ROLE PREKO CUSTOM TABELA I KOLONA
        u query-ju znak ? ce se iz url-a preneti, jer se u url-u nalazi tacan id dobijen preko login forme
         */
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
        // sa ovim ce da koristi jdbc sa poslatim datasource-om
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // ukazujem da koristi obicnu http autentifikaciju
        httpSecurity.httpBasic(Customizer.withDefaults());

        //onemogucavam csrf (za ovakvu aplikaciju koja nije za javnost ne mora da se koristi)
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
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
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

}
