package tyresz.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/post")
public class PostController {
    @GetMapping()
    @ResponseBody
    public String post(){
        return "post index page";
    }

    @GetMapping("{id}")
    @ResponseBody
    public String idpost(){
        return "view an individual post";
    }

    @GetMapping("/create")
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }

    @PostMapping("/create")
    @ResponseBody
    public String postCreate(){
        return "create a new post";
    }

}
