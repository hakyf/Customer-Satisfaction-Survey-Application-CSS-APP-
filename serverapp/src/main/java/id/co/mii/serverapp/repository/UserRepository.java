package id.co.mii.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.co.mii.serverapp.models.User;

public interface UserRepository extends JpaRepository<User,Long> {}
//     Optional<User> findByUsername(String username);
//     Optional<User> findByUsernameOrEmployeeEmail(String username, String email);

//     @Query("SELECT u FROM User u WHERE u.verificationToken= ?1")
//     public User findByVerificationToken(String token);
// }
