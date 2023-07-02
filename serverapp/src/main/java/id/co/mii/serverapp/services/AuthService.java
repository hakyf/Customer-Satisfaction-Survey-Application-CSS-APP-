package id.co.mii.serverapp.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.LoginRequest;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.models.dto.response.LoginResponse;
import id.co.mii.serverapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    // private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private AppUserDetailService appUserDetailService;

    public User register(UserRequest userRequest, String siteUrl) {

        Employee employee = modelMapper.map(userRequest, Employee.class);
        User user = modelMapper.map(userRequest, User.class);

        // set password bcrypt
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        // set default role
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById((long) 3));
        user.setRoles(roles);

        user.setEmployee(employee);
        employee.setUser(user);

        // String randomCode = RandomString.make(64);
        // user.setVerificationToken(randomCode);
        // user.setExpirationTokenStart(LocalDateTime.now());
        // emailService.sendMessageWithVerification(userRequest, siteUrl,
        // user.getVerificationToken());

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // login request
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        // set principle
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = userRepository
                .findByUsernameOrEmployeeEmail(
                        loginRequest.getUsername(),
                        loginRequest.getUsername())
                .get();

        UserDetails userDetails = appUserDetailService.loadUserByUsername(
                loginRequest.getUsername());

        List<String> authorities = userDetails
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        // login response = username, email, List<String> authorities
        return new LoginResponse(
                user.getUsername(),
                user.getEmployee().getEmail(),
                authorities);
    }

    // public boolean verify(String verificationToken) {
    // User user = userRepository.findByVerificationToken(verificationToken);

    // if (user == null || user.getIsEnabled()) {
    // return false;
    // } else {
    // LocalDateTime codeTimestamp = user.getExpirationTokenStart();
    // LocalDateTime currentTimestamp = LocalDateTime.now();
    // Duration duration = Duration.between(codeTimestamp, currentTimestamp);
    // long secondsPassed = duration.getSeconds();

    // // mengatur durasi expired 5 menit
    // if (secondsPassed > 300) {
    // return false;
    // }

    // user.setVerificationToken(null);
    // user.setIsEnabled(true);
    // userRepository.save(user);

    // return true;
    // }
    // }
}
