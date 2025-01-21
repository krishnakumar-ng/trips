package com.trips.auth.server.service;

import com.trips.auth.server.constants.Role;
import com.trips.auth.server.data.entity.User;
import com.trips.auth.server.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtService {
    private final SecretKey secretKey;
    private final String secretKeyString;

    private final int expirationTime = 7199000;

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey = keyGenerator.generateKey();
            secretKeyString = String.valueOf(secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    @Autowired
    private UserRepository userRepository;

    /**
     * @param userName - username
     * @return Bearer token for the given user
     */
    public String generateToken(String userName) {
        Optional<User> userEntity = userRepository.findByUsername(userName);

        User user;
        Set<Role> roles;
        if (userEntity.isPresent()) {
            user = userEntity.get();
            roles = user.getRoles();
        } else {
            throw new UsernameNotFoundException(userName + " - user is not exist.");
        }

        Date issueDate = new Date();

        //Add roles to the token
        return Jwts.builder()
                .subject(userName)
                .claim("roles", roles.stream()
                        .map(Role::name).collect(Collectors.joining(",")))
                .issuedAt(issueDate)
                .expiration(new Date(issueDate.getTime() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    /**
     * @param token - takes token as input
     * @return - return the user name associated with the token
     */
    public String extractUserName(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * @param token - takes token as input
     * @return - return the roles associated with the token
     */
    public Set<String> extractRoles(String token) {
        return Set.of(Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles", String.class));

        //Commented out the code supported for older versions
//        return Set.of(Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("roles", String.class));
    }

    /**
     * @param token - takes the token as the input
     * @return validate the token and return true or false based on the token's validity
     */
    public boolean isValidToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

        //Commented out the code supported for older versions
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(secretKey)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
