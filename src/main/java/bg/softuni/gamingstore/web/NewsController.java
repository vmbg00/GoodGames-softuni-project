package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    private final GalleryService galleryService;

    public NewsController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/news")
    public String news(Model model){
        model.addAttribute("pictures", this.galleryService.getAllPics());
        return "news";
    }
}
