package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.views.ShoppingCartGamesViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void addToCart(Long id);

    List<ShoppingCartGamesViewModel> getAllGamesInCart();

    BigDecimal totalPriceOfAllGames();

    void clearCurrentUserCart();
}
