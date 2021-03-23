package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final NewsService newsService;

    public HomeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allLatestNews", this.newsService.getAllNews());
        return "home";
    }
}
