package rikkei.projectmodule4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rikkei.projectmodule4.model.entity.Roles;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Roles,Long>
{
    Optional<Roles> findRoleByRoleName(String roleName);

}
