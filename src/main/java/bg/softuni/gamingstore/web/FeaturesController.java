package bg.softuni.gamingstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeaturesController {

    @GetMapping("/forum")
    public String forum(){
        return "forum";
    }
}
