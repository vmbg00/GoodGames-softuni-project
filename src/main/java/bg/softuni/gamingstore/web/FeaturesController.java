package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FeaturesController {

    private final EmailService emailService;

    public FeaturesController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/coming-soon")
    public ModelAndView comingSoon(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coming-soon");
        return modelAndView;
    }

    @PostMapping("/coming-soon")
    @ResponseBody
    public ModelAndView comingSoonConfirm(@RequestParam String email){
        ModelAndView modelAndView = new ModelAndView();
        this.emailService.sendMail(email);

        modelAndView.setViewName("coming-soon");
        return modelAndView;
    }
}
