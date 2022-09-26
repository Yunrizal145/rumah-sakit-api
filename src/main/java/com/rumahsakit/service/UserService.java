package com.rumahsakit.service;

import com.rumahsakit.model.Dokter;
import com.rumahsakit.model.Perawat;
import com.rumahsakit.model.Staff;
import com.rumahsakit.model.User;
import com.rumahsakit.util.JwtUtil;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Transactional
    public User superAdmin(@Observes StartupEvent event){
        User user = new User();
        Optional<User> userOptional = User.find("username = ?1", "rizal").firstResultOptional();

        if (userOptional.isEmpty()) {

            user.setName("HAMMAD YUNRIZAL AUSHAF");
            user.setUsername("rizal");
            user.setPassword("20200040145");
            user.setEmail("hamad.yunrizal@gmail.com");
            user.setPhoneNumber("089540467293");
            user.setUserType("admin");

            user.persist();
        }
        return user;
    }


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

        JsonObject response = new JsonObject();
        response.put("data", user);
        response.put("Message", "Anda Berhasil Login >_< ");

        return Response.ok().entity(response.getMap()).build();

    }


    // Validation Register
    @Transactional
    public Response register(JsonObject request){
        User user = new User();
        user.setName(request.getString("name"));
        user.setUsername(request.getString("username"));
        user.setPassword(request.getString("password"));

        user.persist();

        String token = JwtUtil.generateJwt(user);
        JsonObject response = new JsonObject();
        response.put("data", user);
        response.put("token", token);

        return Response.ok().entity(response.getMap()).build();
    }

}
