package bg.softuni.gamingstore.web;

import bg.softuni.gamingstore.models.binding.BillingHistoryBindingModel;
import bg.softuni.gamingstore.models.binding.StoreAddGameBindingModel;
import bg.softuni.gamingstore.models.services.BillingHistoryServiceModel;
import bg.softuni.gamingstore.models.services.StoreAddGameServiceModel;
import bg.softuni.gamingstore.services.BillingHistoryService;
import bg.softuni.gamingstore.services.GameService;
import bg.softuni.gamingstore.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class StoreController {

    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final BillingHistoryService billingHistoryService;
    private final ShoppingCartService shoppingCartService;

    public StoreController(GameService gameService, ModelMapper modelMapper, BillingHistoryService billingHistoryService, ShoppingCartService shoppingCartService) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.billingHistoryService = billingHistoryService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/store")
    public String store(Model model){

        model.addAttribute("availableGames", this.gameService.getAllGames());
        model.addAttribute("availableGamesCount", this.gameService.countAllGames());

        return "store";
    }

    @GetMapping("/store/add/{id}")
    public String addToCart(@PathVariable Long id){
        this.shoppingCartService.addToCart(id);

        return "redirect:/store";
    }

    @DeleteMapping("/store/delete/{id}")
    public String deleteGame(@PathVariable Long id){
        this.gameService.deleteGame(id);

        return "redirect:/store";
    }

    @GetMapping("/store-cart")
    public String storeCart(Model model){

        model.addAttribute("gamesInCart", this.shoppingCartService.getAllGamesInCart());

        return "store-cart";
    }

    @GetMapping("/store-cart/remove/{id}")
    public String removeItem(@PathVariable Long id){
        this.shoppingCartService.removeItemFromCart(id);

        return "redirect:/store-cart";
    }

    @GetMapping("/store-checkout")
    public String storeCheckout(Model model){
        model.addAttribute("billingHistoryBindingModel", new BillingHistoryBindingModel());
        model.addAttribute("gamesInCart", this.shoppingCartService.getAllGamesInCart());
        model.addAttribute("totalPriceOfAllGames", this.shoppingCartService.totalPriceOfAllGames());

        return "store-checkout";
    }

    @PostMapping("/store-checkout/buy")
    public String storeCheckoutConfirm(@Valid BillingHistoryBindingModel billingHistoryBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("billingHistoryBindingModel", billingHistoryBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.billingHistoryBindingModel", bindingResult);

            return "redirect:store-checkout";
        }
        this.billingHistoryService.addToHistory
                (this.modelMapper.map(billingHistoryBindingModel, BillingHistoryServiceModel.class));
        this.gameService.addGamesToUser();
        this.shoppingCartService.clearCurrentUserCart();

        return "redirect:/";
    }

    @GetMapping("/store/add-new-game")
    public String addGameToStore(Model model){
        if (!model.containsAttribute("storeAddGameBindingModel")){
            model.addAttribute("storeAddGameBindingModel", new StoreAddGameBindingModel());
        }
        return "store-add-game";
    }

    @PostMapping("/store/add-new-game")
    public String addGameToStoreConfirm(@Valid StoreAddGameBindingModel storeAddGameBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("storeAddGameBindingModel", storeAddGameBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.storeAddGameBindingModel", bindingResult);

            return "redirect:add-new-game";
        }

        this.gameService.addNewGameToStore(this.modelMapper.map(storeAddGameBindingModel, StoreAddGameServiceModel.class));

        return "redirect:/";
    }

}
