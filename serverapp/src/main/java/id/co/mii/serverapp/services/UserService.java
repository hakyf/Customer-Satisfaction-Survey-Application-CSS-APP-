package id.co.mii.serverapp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Employee;
import id.co.mii.serverapp.models.Role;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.UserRequest;
import id.co.mii.serverapp.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;

    public List<User> getAll(){
        return userRepository.findAll();
        
    }

    public User getById(Long id){
        return userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found !"));
    }

    public User create(UserRequest userRequest) {
        Employee employee = modelMapper.map(userRequest, Employee.class);
        User user = modelMapper.map(userRequest, User.class);

        // SET DEFAULT ROLE
        // List<Role> roles = new ArrayList<>();
       
        // roles.add(roleService.getById());
        // user.setRoles(roles);

        user.setEmployee(employee);
        employee.setUser(user);

        return userRepository.save(user);
    }

    public User update(Long id, User user){
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Long id){
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }

    //add role
    public User addRole(Long id, Role role){
        // user ada atau tidak
        User user = getById(id);

        // cek role ada atau tidak
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));

        // set role
        user.setRoles(roles);

        // simpan
        return userRepository.save(user);
    }

}
