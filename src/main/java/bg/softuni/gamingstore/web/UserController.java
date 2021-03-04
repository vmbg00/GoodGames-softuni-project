package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.models.binding.LoginBindingModel;
import bg.softuni.gamingstore.models.binding.RegisterBindingModel;
import bg.softuni.gamingstore.models.services.LoginServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("loginBindingModel")){
            model.addAttribute("loginBindingModel", new LoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid LoginBindingModel loginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBindingModel", bindingResult);

            return "redirect:login";
        }

        if (this.userService.authenticate(loginBindingModel.getUsername(), loginBindingModel.getPassword())){
            this.userService.login(modelMapper.map(loginBindingModel, LoginServiceModel.class));

            return "redirect:/";
        }
        else {
            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("registerBindingModel")){
            model.addAttribute("registerBindingModel", new RegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterBindingModel registerBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() ||
        !registerBindingModel.getPassword().equals(registerBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBindingModel", bindingResult);

            return "redirect:register";
        }
        this.userService.register(this.modelMapper.map(registerBindingModel, RegisterServiceModel.class));

        return "redirect:/";
    }


}
