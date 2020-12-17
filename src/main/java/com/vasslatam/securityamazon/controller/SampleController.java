/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.securityamazon.controller;

import com.vasslatam.securityamazon.request.LoginRequest;
import static com.vasslatam.securityamazon.util.Constants.SECRET_KEY;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego Silva diego.silva at vasslatam.com
 */
@RestController
public class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    @GetMapping(
            path = "/auth/secure",
            produces = TEXT_PLAIN_VALUE
    )
    public String showSecure(Authentication auth) {
        LOGGER.info("entrando a /secure. ");
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        Object principal = auth.getPrincipal();
        LOGGER.info("roles:{}", roles);
        LOGGER.info("auth:{}", auth);
        LOGGER.info("name:{}", auth.getName());
        return "Hola " + principal;
    }

    @PostMapping(
            path = "auth/login",
            consumes = APPLICATION_JSON_VALUE,
            produces = TEXT_PLAIN_VALUE
    )
    public String login(@RequestBody LoginRequest loginRequest) {
        return getJWTToken(loginRequest.getUsername() + ':' + loginRequest.getPassword()); //cualquier valor generado
    }

    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("USER1,USER2");

        String token = Jwts
                .builder()
                .setId("vasslatam")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(10, MINUTES)))
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes()).compact();

        return token;
    }

}
