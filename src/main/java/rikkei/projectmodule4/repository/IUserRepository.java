package rikkei.projectmodule4.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rikkei.projectmodule4.model.entity.User;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);


}
