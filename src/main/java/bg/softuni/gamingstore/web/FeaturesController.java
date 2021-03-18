package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeaturesController {

    private final EmailService emailService;

    public FeaturesController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/coming-soon")
    public String comingSoon(){
        return "coming-soon";
    }

    @PostMapping("/coming-soon")
    @ResponseBody
    public String comingSoonConfirm(@RequestParam String email){
        this.emailService.sendMail(email);

        return "redirect:/";
    }
}
