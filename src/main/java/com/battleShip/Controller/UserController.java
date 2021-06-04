package com.battleShip.Controller;

import com.battleShip.Model.Entities.UserLogin;
import com.battleShip.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public @ResponseBody
    ResponseEntity<Object> findAllUser() {
        return ResponseEntity.ok().body(userService.findAllUser());
    }

    @GetMapping(path = "{email}/{password}")
    public @ResponseBody
    ResponseEntity<Object> getLogin(@PathVariable("email") String email, @PathVariable("password") String password) {
        return ResponseEntity.ok().body(userService.getLogin(email,password));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody UserLogin user)
    {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @GetMapping(path = "{id}")
    public @ResponseBody
    ResponseEntity<Object> getMapById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
}
