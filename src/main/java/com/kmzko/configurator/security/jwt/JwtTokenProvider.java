package com.kmzko.configurator.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmzko.configurator.entity.user.Role;
import com.kmzko.configurator.entity.user.User;
import com.kmzko.configurator.security.JwtUserDetailService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.access.expired}")
    private long iat;
    @Value("${jwt.refresh.expired}")
    private long iatRefresh;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(JwtUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(User user) {
        Date now = new Date();
        Date timeOfDeath = new Date(now.getTime() + iat);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claim(user))
                .setIssuedAt(now)
                .setExpiration(timeOfDeath)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken(User user) {
        Date now = new Date();
        Date timeOfDeath = new Date(now.getTime() + iatRefresh);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setIssuedAt(now)
                .setExpiration(timeOfDeath)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Claims claim(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", getRoleNames(user.getRoles()));

        return claims;
    }

    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUsernameWithoutCheckExpired(String token) {
        return customParser(token);
    }

    private String customParser(String token) {
        try {
            String[] a = token.split("[.]+");
            String base64UrlEncodedPayload = a[1];
            String payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedPayload);
            Claims claims = null;
            if (payload.charAt(0) == '{' && payload.charAt(payload.length() - 1) == '}') {
                Map<String, Object> claimsMap = new ObjectMapper().readValue(payload, Map.class);
                claims = new DefaultClaims(claimsMap);
            }

            return claims.getSubject();
        }
        catch (Exception ex) {
            throw new SignatureException("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if (token != null) {
            return token;
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private List<String> getRoleNames(Set<Role> userRoles) {
        return userRoles.stream().map(role -> role.getName())
                .collect(Collectors.toList());
    }
}
