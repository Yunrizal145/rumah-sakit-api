package com.rumahsakit.util;

import com.rumahsakit.model.User;
import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;

@Singleton
public class JwtUtil {

    public static String generateJwt(User user){

        HashSet<String> roles = new HashSet<>(Arrays.asList("super_admin", "admin", "user"));

        return Jwt.issuer("https://example.com/issuer")
                .subject("Token")
                .upn(user.getName())
                .groups(roles)
                .claim("name", user.getUsername())
                .claim("password", user.getPassword())
                .sign();
    }

}
