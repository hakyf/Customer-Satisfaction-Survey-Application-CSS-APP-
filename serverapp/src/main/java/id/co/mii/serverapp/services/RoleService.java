package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Role;

import id.co.mii.serverapp.repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Long i) {
        return roleRepository
                .findById(i)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not exists!!!"));
    }

    public Role create(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Role already exists!!!");
        }
        return roleRepository.save(role);
    }

    public Role update(Long id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role delete(Long id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;

    }
}
