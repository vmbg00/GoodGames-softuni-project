package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.events.NewUserEventPublisher;
import bg.softuni.gamingstore.models.binding.ChangeUserRoleBindingModel;
import bg.softuni.gamingstore.models.binding.DeleteUserBindingModel;
import bg.softuni.gamingstore.models.binding.DemoteUserBindingModel;
import bg.softuni.gamingstore.models.binding.RegisterBindingModel;
import bg.softuni.gamingstore.models.services.ChangeUserRoleServiceModel;
import bg.softuni.gamingstore.models.services.DeleteUserServiceModel;
import bg.softuni.gamingstore.models.services.DemoteUserServiceModel;
import bg.softuni.gamingstore.models.services.RegisterServiceModel;
import bg.softuni.gamingstore.services.BillingHistoryService;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BillingHistoryService billingHistoryService;
    private final ModelMapper modelMapper;
    private final NewUserEventPublisher publisher;

    public UserController(UserService userService, BillingHistoryService billingHistoryService, ModelMapper modelMapper, NewUserEventPublisher publisher) {
        this.userService = userService;
        this.billingHistoryService = billingHistoryService;
        this.modelMapper = modelMapper;
        this.publisher = publisher;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login(){
        return "login";
    }

//    @PostMapping("/login")
//    public String loginConfirm(@Valid LoginBindingModel loginBindingModel,
//                               BindingResult bindingResult,
//                               RedirectAttributes redirectAttributes){
//
//        if (bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginBindingModel", bindingResult);
//
//            return "redirect:login";
//        }
//
//        if (this.userService.authenticate(loginBindingModel.getUsername(), loginBindingModel.getPassword())){
//
//            return "redirect:/";
//        }
//        else {
//            redirectAttributes.addFlashAttribute("loginBindingModel", loginBindingModel);
//            redirectAttributes.addFlashAttribute("notFound", true);
//
//            return "redirect:login";
//        }
//    }

    @GetMapping("/games")
    public String allGames(){
        return "user-games-display";
    }

    @GetMapping("/user-games")
    public String allGamesDisplay(Model model){
        model.addAttribute("allUserGames", this.userService.getAllUserGames());
        return "user-games";
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String register(Model model){
        if (!model.containsAttribute("registerBindingModel")){
            model.addAttribute("registerBindingModel", new RegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerConfirm(@Valid RegisterBindingModel registerBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() ||
        !registerBindingModel.getPassword().equals(registerBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (this.userService.userNameExists(registerBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        this.userService.register(this.modelMapper.map(registerBindingModel, RegisterServiceModel.class));
        this.publisher.publishEvent(registerBindingModel.getUsername());

        return "redirect:/";
    }

    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                    String username,
                                    RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();


        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        modelAndView.setViewName("redirect:/users/login");

        return modelAndView;
    }

    @GetMapping("/change-user-role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String changeRole(Model model){
        if (!model.containsAttribute("changeUserRoleBindingModel")){
            model.addAttribute("changeUserRoleBindingModel", new  ChangeUserRoleBindingModel());
        }
        model.addAttribute("allUsersList", this.userService.getAllUsers());
        return "role-add";
    }

    @PostMapping("/change-user-role")
    public String changeRoleConfirm(@Valid ChangeUserRoleBindingModel changeUserRoleBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("changeUserRoleBindingModel", changeUserRoleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeUserRoleBindingModel", bindingResult);

            return "redirect:/change-user-role";
        }

        this.userService.changeUserRole(this.modelMapper.map(changeUserRoleBindingModel, ChangeUserRoleServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/demote-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String demoteUser(Model model){
        if (!model.containsAttribute("demoteUserBindingModel")){
            model.addAttribute("demoteUserBindingModel", new DemoteUserBindingModel());
        }
        model.addAttribute("allUsersList", this.userService.getAllWithAdminRoles());
        return "demote-user";
    }

    @PostMapping("/demote-user")
    public String demoteUserConfirm(@Valid DemoteUserBindingModel demoteUserBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("demoteUserBindingModel", demoteUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.demoteUserBindingModel", bindingResult);

            return "redirect:/demote-user";
        }

        this.userService.demoteUser(this.modelMapper.map(demoteUserBindingModel, DemoteUserServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/delete-user")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String deleteUser(Model model){
        if (!model.containsAttribute("deleteUserBindingModel")){
            model.addAttribute("deleteUserBindingModel", new DeleteUserBindingModel());
        }
        model.addAttribute("allUsersAndAdmins", this.userService.getAllUsersAndAdmins());
        return "delete-user";
    }

    @PostMapping("/delete-user")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String deleteUserConfirm(@Valid DeleteUserBindingModel deleteUserBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("deleteUserBindingModel", deleteUserBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deleteUserBindingModel", bindingResult);

            return "redirect:/delete-user";
        }

        this.userService.deleteUser(this.modelMapper.map(deleteUserBindingModel, DeleteUserServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/billing")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String billingHistories(Model model){
        model.addAttribute("allBillingHistories", this.billingHistoryService.getAllBillingHistories());
        return "billing-history";
    }

}
