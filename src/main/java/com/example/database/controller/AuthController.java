    package com.example.database.controller;

    import com.example.database.Model.dto.LoginDTO;
    import com.example.database.service.LoginService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public class AuthController {
        @Autowired
        LoginService loginService;
        @PostMapping()
        public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
            if(loginService.login(loginDTO.getUsername(), loginDTO.getPassword()).equals("admin")){
                return ResponseEntity.ok().body("login success");
            }
            else
                return ResponseEntity.ok().body("login failed");
        }

    }
