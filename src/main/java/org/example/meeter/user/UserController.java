package org.example.meeter.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody Map<String, String> map) {
        UserDto dto = userService.addUser(map);
        return ResponseEntity.ok(dto);
    }
}
