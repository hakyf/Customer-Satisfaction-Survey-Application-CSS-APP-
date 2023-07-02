package id.co.mii.serverapp.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.LoginRequest;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.models.dto.response.LoginResponse;
import id.co.mii.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {
    
    private AuthService authService;

    @PostMapping("/register")
  public User create(@RequestBody UserRequest userRequest, HttpServletRequest request) {
    return authService.register(userRequest, request.getRequestURL().toString().replace(request.getServletPath(), ""));
  }
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/verify")
    public ResponseEntity verifyUser(@Param("token") String token) {
      if (authService.verify(token)) {
        return new ResponseEntity<>("Verifikasi Email Telah Berhasil", HttpStatus.OK);
      } else {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
      }
    }
}