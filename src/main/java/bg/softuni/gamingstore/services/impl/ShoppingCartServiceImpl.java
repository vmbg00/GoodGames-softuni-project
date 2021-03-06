package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.views.ShoppingCartGamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import bg.softuni.gamingstore.services.ShoppingCartService;
import bg.softuni.gamingstore.services.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final GamesRepository gamesRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;

    public ShoppingCartServiceImpl(GamesRepository gamesRepository, ShoppingCartRepository shoppingCartRepository, UserService userService) {
        this.gamesRepository = gamesRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
    }

    @Override
    public void addToCart(Long id) {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        GameEntity game = this.gamesRepository.findById(id).get();
        UserEntity user = this.userService.getUserEntity();
        shoppingCartEntity.setGames(game);
        shoppingCartEntity.setUser(user);
        this.shoppingCartRepository.save(shoppingCartEntity);
    }

    @Override
    public List<ShoppingCartGamesViewModel> getAllGamesInCart() {
        List<ShoppingCartGamesViewModel> viewModels = this.shoppingCartRepository.findAllByUser(this.userService.getUserEntity()).stream()
                .map(shoppingCartEntity -> {
                    ShoppingCartGamesViewModel viewModel = new ShoppingCartGamesViewModel();

                    viewModel.setId(shoppingCartEntity.getGames().getId());
                    viewModel.setName(shoppingCartEntity.getGames().getName());
                    viewModel.setImageUrl(shoppingCartEntity.getGames().getImageUrl());
                    viewModel.setPrice(shoppingCartEntity.getGames().getPrice());

                    return viewModel;
                }).collect(Collectors.toList());

        return viewModels;
    }

    @Override
    public BigDecimal totalPriceOfAllGames() {
        List<ShoppingCartEntity> allByUser = this.shoppingCartRepository.findAllByUser(this.userService.getUserEntity());

        int result = 0;
        for (ShoppingCartEntity shoppingCartEntity : allByUser) {
           result += shoppingCartEntity.getGames().getPrice().intValue();
        }

        return BigDecimal.valueOf(result);
    }

    @Override
    public void clearCurrentUserCart() {
        UserEntity userEntity = this.userService.getUserEntity();
        List<ShoppingCartEntity> all = this.shoppingCartRepository.findAllByUser(userEntity);

        for (ShoppingCartEntity shoppingCartEntity : all) {
            this.shoppingCartRepository.delete(shoppingCartEntity);
        }
    }

    @Override
    public boolean removeItemFromCart(Long id) {
        List<ShoppingCartEntity> allByUser = null;
        try {
            allByUser = this.shoppingCartRepository.findAllByUser(this.userService.getUserEntity());
        } catch (Exception ignored){

        }

        for (ShoppingCartEntity shoppingCartEntity : allByUser) {
            if (shoppingCartEntity.getGames().getId().equals(id)){
                GameEntity gameEntity = this.gamesRepository.findById(id).get();
                this.shoppingCartRepository.deleteShoppingCartEntityByGames_Id(gameEntity.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfGameIsAlreadyInCart(Long id, UserEntity userEntity) {
        List<ShoppingCartEntity> cartEntities = this.shoppingCartRepository.findAllByUser(userEntity);

        for (ShoppingCartEntity cartEntity : cartEntities) {
            if (cartEntity.getGames().getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ShoppingCartEntity> findAllGames() {
        return this.shoppingCartRepository.findAll();
    }

    @Override
    public List<ShoppingCartEntity> getAllCartsWithGame(String name) {
        return this.shoppingCartRepository.getShoppingCartEntityByGames_Name(name);
    }

    @Override
    public List<ShoppingCartEntity> getAllCartsWithUsername(String name) {
        return this.shoppingCartRepository.getShoppingCartEntityByUser_Username(name);
    }
}
