package bg.softuni.gamingstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/store")
    public String store(){
        return "store";
    }

    @GetMapping("/store-product")
    public String storeProduct(){
        return "store-product";
    }

}
