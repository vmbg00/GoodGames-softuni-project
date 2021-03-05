package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    private final GameService gameService;

    public StoreController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/store")
    public String store(Model model){

        model.addAttribute("availableGames", this.gameService.getAllGames());

        return "store";
    }

    @GetMapping("/store-product")
    public String storeProduct(){
        return "store-product";
    }

    @GetMapping("/store-cart")
    public String storeCart(){
        return "store-cart";
    }

    @GetMapping("/store-checkout")
    public String storeCheckout(){
        return "store-checkout";
    }

}
