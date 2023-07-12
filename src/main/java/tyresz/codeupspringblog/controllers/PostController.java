package tyresz.codeupspringblog.controllers;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tyresz.codeupspringblog.models.EmailService;
import tyresz.codeupspringblog.models.Post;
import tyresz.codeupspringblog.models.User;
import tyresz.codeupspringblog.repositories.PostRepository;
import tyresz.codeupspringblog.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("")
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    private final EmailService emailService;

    @GetMapping()
    public String home(){
        return "home";
    }
    @GetMapping("/post")
    public String index(Model model){
//        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userDao.getReferenceById(loggedIn.getId());
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts",posts);
        return "post/index";
    }
    @GetMapping("post/{id}")
    public String FetchById(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isEmpty()){
            return "404";
        }
        Post post = optionalPost.get();
        model.addAttribute("post",post);
        return "post/show";
    }

    @GetMapping("post/create")
    public String showForm(Model model){
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("post/create")
    public String createPost(@ModelAttribute Post post){
        Optional<User> optionalUser = userDao.findById(1L);
        if (optionalUser.isEmpty()){
            return "404";
        }
        User user = optionalUser.get();
        post.setUser(user);
        emailService.prepareAndSend(post,"There Was A New Post Added!","Dear, " + user.getUsername() +
                "\n" +
                "I hope this email finds you well. I wanted to reach out and share some exciting news with you - you have a brand new post! Your content has been published and is now available for all to see.\n" +
                "\n" +
                "Your hard work and dedication have paid off, and I am thrilled to inform you that your latest post has been successfully uploaded to the platform. Whether it's an article, blog post, or social media update, your valuable contribution is now live and ready to make an impact.\n" +
                "\n" +
                "This achievement is a testament to your creativity, knowledge, and ability to engage your audience. I'm confident that your post will resonate with readers and generate meaningful interactions.\n" +
                "\n" +
                "Now that your content is out in the world, I encourage you to take some time to celebrate this accomplishment. Share your post with your network, encourage discussions around the topic, and relish in the feedback you receive.\n" +
                "\n" +
                "Remember, this is just the beginning of a journey filled with endless possibilities. Your voice matters, and your posts have the potential to inspire, educate, and entertain others. Keep up the fantastic work, and continue to share your unique perspective with the world.\n" +
                "\n" +
                "Once again, congratulations on your new post! I can't wait to see what amazing content you create in the future.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                user.getUsername());
        postDao.save(post);
        return "redirect:/post";
    }
    @GetMapping("post/{id}/edit")
    public String showEditForm(@PathVariable Long id, @ModelAttribute("post") Post post) {
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isEmpty()){
            return "404";
        }
        return "post/edit";
    }
    @PostMapping("post/{id}/edit")
    public String EditForm(@PathVariable Long id, @ModelAttribute Post post){
        String title = post.getTitle();
        String body = post.getBody();
        Optional<Post> optionalPost = postDao.findById(id);
        if (optionalPost.isEmpty()){
            return "404";
        }
        Post existingPost = optionalPost.get();
        existingPost.setTitle(title);
        existingPost.setBody(body);
        postDao.save(existingPost);
        return "redirect:/post/{id}";
    }
}


