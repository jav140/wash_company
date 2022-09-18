package uz.jl.task2_2.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.task2_2.domains.sub.AuthRole;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/15:51 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */
public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}
