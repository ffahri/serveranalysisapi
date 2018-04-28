package com.webischia.analysisbackend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;


    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override //nerelere izin var nerelerde izin yok -- mobil uyg olacağı için heryer yasak
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests()
                //.antMatchers("/api/register/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/config/**").hasRole("Admin").anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/giris")
                .permitAll()
                .and()
                .httpBasic();

    }

}
