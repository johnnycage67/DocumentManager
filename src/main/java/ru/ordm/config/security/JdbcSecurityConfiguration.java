package ru.ordm.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.ordm.utils.crypto.DesEncoder;

import java.sql.ResultSet;

@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter{



    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
        RowMapper<User> userRowMapper = (ResultSet rs, int i) ->
                new User(
                        rs.getString("NAME"),
                        rs.getString("PASSWORD"),//rs.getString("PASSWORD"),
                        true,//rs.getBoolean("ENABLED"),
                        true,//rs.getBoolean("ENABLED"),
                        true,//rs.getBoolean("ENABLED"),
                        true,//rs.getBoolean("ENABLED"),
            //     AuthorityUtils.createAuthorityList((String) rs.getObject("ROLE"))
              //       AuthorityUtils.createAuthorityList(rs.getString("ROLE"))
                        AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN")
                        );



        return username ->
                jdbcTemplate.queryForObject("SELECT name,password from SYS.USER$ where upper(NAME) = upper(?) "
                       ,userRowMapper, username);
    }

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
         //authentication.getCredentials()
  //      provider.setPasswordEncoder(authentication.getName()+authentication.getCredentials());
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new DesEncoder());
        auth.eraseCredentials(false);

    }



}
