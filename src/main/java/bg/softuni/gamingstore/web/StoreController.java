package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.services.GameService;
import bg.softuni.gamingstore.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StoreController {

    private final GameService gameService;
    private final ShoppingCartService shoppingCartService;

    public StoreController(GameService gameService, ShoppingCartService shoppingCartService) {
        this.gameService = gameService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/store")
    public String store(Model model){

        model.addAttribute("availableGames", this.gameService.getAllGames());

        return "store";
    }

    @GetMapping("/store/add/{id}")
    public String addToCart(@PathVariable Long id){
        this.shoppingCartService.addToCart(id);

        return "redirect:/store";
    }

    @GetMapping("/store/delete/{id}")
    public String deleteGame(@PathVariable Long id){
        this.gameService.deleteGame(id);

        return "redirect:/store";
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
