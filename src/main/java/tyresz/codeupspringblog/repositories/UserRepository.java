package tyresz.codeupspringblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tyresz.codeupspringblog.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
