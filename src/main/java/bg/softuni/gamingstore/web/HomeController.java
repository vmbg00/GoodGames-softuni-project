package bg.softuni.gamingstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/coming-soon")
    public String comingSoon(){
        return "coming-soon";
    }
}
