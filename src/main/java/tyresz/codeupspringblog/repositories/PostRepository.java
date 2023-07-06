package tyresz.codeupspringblog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tyresz.codeupspringblog.models.Post;
public interface PostRepository extends JpaRepository<Post,Long> {
}

