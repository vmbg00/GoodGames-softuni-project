package bg.softuni.gamingstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/coming-soon")
    public String comingSoon(){
        return "coming-soon";
    }

    @GetMapping("/news")
    public String news(){
        return "news";
    }
}
