package bg.softuni.gamingstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    public String gallery(){
        return "gallery";
    }
}
