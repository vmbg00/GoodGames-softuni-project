package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.models.binding.NewsAddBindingModel;
import bg.softuni.gamingstore.models.services.NewsAddServiceModel;
import bg.softuni.gamingstore.services.GalleryService;
import bg.softuni.gamingstore.services.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class NewsController {

    private final GalleryService galleryService;
    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsController(GalleryService galleryService, NewsService newsService, ModelMapper modelMapper) {
        this.galleryService = galleryService;
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/news")
    public String news(Model model){
        model.addAttribute("pictures", this.galleryService.getAllPics());
        model.addAttribute("news", this.newsService.getAllNews());
        return "news";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model){
        if (!model.containsAttribute("newsAddBindingModel")){
            model.addAttribute("newsAddBindingModel", new NewsAddBindingModel());
        }

        return "news-add";
    }

    @PostMapping("/news/add")
    public String newsAddConfirm(@Valid NewsAddBindingModel newsAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("newsAddBindingModel", newsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newsAddBindingModel", bindingResult);

            return "redirect:news-add";
        }

        this.newsService.addNewNews(this.modelMapper.map(newsAddBindingModel, NewsAddServiceModel.class));

        return "redirect:/";
    }
}
