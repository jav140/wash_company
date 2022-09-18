package uz.jl.task2_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.task2_2.domains.AuthUser;

import java.util.Optional;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/12:09 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);

    void deleteByUsername(String username);
}
