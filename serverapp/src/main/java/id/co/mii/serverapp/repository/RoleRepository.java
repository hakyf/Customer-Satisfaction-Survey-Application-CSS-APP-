package id.co.mii.serverapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Role;

public interface RoleRepository extends JpaRepository <Role, Long> {
    
}
