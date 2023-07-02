package id.co.mii.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmployeeEmail(String username, String email);

    @Query(value = "INSERT INTO user_role(user_id, role_id) VALUES(:idUser, :idRole)", nativeQuery = true)
    public boolean createUserRole(Integer idUser, Integer idRole);
}