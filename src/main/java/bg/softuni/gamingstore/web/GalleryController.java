package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.models.binding.GalleryAddBindingModel;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.services.GalleryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class GalleryController {

    private final GalleryService galleryService;
    private final ModelMapper modelMapper;

    public GalleryController(GalleryService galleryService, ModelMapper modelMapper) {
        this.galleryService = galleryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("pictures", this.galleryService.getAllPics());
        return "gallery";
    }

    @GetMapping("/gallery/add")
    public String galleryAdd(Model model){
        if (!model.containsAttribute("galleryAddBindingModel")){
            model.addAttribute("galleryAddBindingModel", new GalleryAddBindingModel());
        }

        return "gallery-add";
    }

    @PostMapping("/gallery/add")
    public String galleryAddConfirm(@Valid GalleryAddBindingModel galleryAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("galleryAddBindingModel", galleryAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.galleryAddBindingModel", bindingResult);

            return "redirect:add";
        }

        this.galleryService.add(this.modelMapper.map(galleryAddBindingModel, GalleryAddServiceModel.class));

        return "redirect:/gallery";
    }
}
