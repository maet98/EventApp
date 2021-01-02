package edu.pucmm.microserviciousuario.Repository;

import edu.pucmm.microserviciousuario.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findAll();
    User findByEmail(String email);
    @Query("SELECT COUNT(1) FROM User u WHERE u.email = :email")
    int existsByEmail(@Param("email") String email);
}