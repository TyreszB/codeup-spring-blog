package tyresz.codeupspringblog.controllers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tyresz.codeupspringblog.models.Post;
import tyresz.codeupspringblog.models.User;
import tyresz.codeupspringblog.repositories.PostRepository;
import tyresz.codeupspringblog.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    @GetMapping()
    public String index(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts",posts);
        return "post/index";
    }
    @GetMapping("{id}")
    public String FetchById(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isEmpty()){
            return "404";
        }
        Post post = optionalPost.get();
        model.addAttribute("post",post);
        return "post/show";
    }

    @GetMapping("/create")
    public String showForm(){
        return "post/create";
    }

    @PostMapping("/create")
    public String createPost(
            @RequestParam String title
            , @RequestParam String body){
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        Optional<User> optionalUser = userDao.findById(2L);
        if (optionalUser.isEmpty()){
            return "404";
        }
        User user = optionalUser.get();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/post";
    }
}


