package tyresz.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping()
    @ResponseBody
    public String land(){
       return "<h1>This is your landing page!</h1>";
    }
}
