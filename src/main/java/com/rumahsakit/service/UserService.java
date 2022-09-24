package com.rumahsakit.service;

import com.rumahsakit.model.User;
import com.rumahsakit.util.JwtUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    // Validation Login
    public Response login(JsonObject request){
        String username = request.getString("username");
        String password = request.getString("password");

        Optional<User> userOptional = User.find("username = ?1", username).firstResultOptional();
        if (userOptional.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("USER_NOT_FOUND").build();
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("WRONG_PASSWORD").build();
        }

        String token = JwtUtil.generateJwt(user);
        JsonObject response = new JsonObject();
        response.put("data", user);
        response.put("token", token);

        return Response.ok().entity(response.getMap()).build();

    }


    // Validation Register
    public Response register(JsonObject request){
        String name = request.getString("name");
        String username = request.getString("username");
        String password = request.getString("password");

        User user = persistResponce(name, username, password);

        String token = JwtUtil.generateJwt(user);
        JsonObject response = new JsonObject();
        response.put("data", user);
        response.put("token", token);

        return Response.ok().entity(response.getMap()).build();
    }


    @Transactional
    public User persistResponce(String name, String username, String password){
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        user.persist();

        return user;
    }

}
