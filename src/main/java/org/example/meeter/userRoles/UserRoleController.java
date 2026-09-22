package org.example.meeter.userRoles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/")
    public String test() {
        return "DZIAŁA";
    }

    @PostMapping("/")
    public ResponseEntity<UserRole> addRole(@RequestBody Map<String, String> map) {
        System.out.println("Dodaję...");
        String name = map.get("name");
        UserRole userRole = userRoleService.addUserRole(name);
        return ResponseEntity.ok().body(userRole);
    }
}
