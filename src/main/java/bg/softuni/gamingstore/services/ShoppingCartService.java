package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.views.ShoppingCartGamesViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    void addToCart(Long id);

    List<ShoppingCartGamesViewModel> getAllGamesInCart();

    BigDecimal totalPriceOfAllGames();

    void clearCurrentUserCart();

    void removeItemFromCart(Long id);

    boolean checkIfGameIsAlreadyInCart(Long id);

    List<ShoppingCartEntity> findAllGames();

    List<ShoppingCartEntity> getAllCartsWithGame(String name);

    List<ShoppingCartEntity> getAllCartsWithUsername(String name);
}
