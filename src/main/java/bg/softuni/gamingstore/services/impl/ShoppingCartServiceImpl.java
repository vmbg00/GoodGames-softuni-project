package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.views.ShoppingCartGamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final GamesRepository gamesRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(GamesRepository gamesRepository, UserRepository userRepository, ModelMapper modelMapper, ShoppingCartRepository shoppingCartRepository) {
        this.gamesRepository = gamesRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addToCart(Long id) {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        GameEntity game = this.gamesRepository.findById(id).get();
        UserEntity user = getUserEntity();
        shoppingCartEntity.setGames(game);
        shoppingCartEntity.setUser(user);
        this.shoppingCartRepository.save(shoppingCartEntity);
    }

    private UserEntity getUserEntity() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserEntity user = this.userRepository.findByUsername(username).get();
        return user;
    }

    @Override
    public List<ShoppingCartGamesViewModel> getAllGamesInCart() {
        List<ShoppingCartGamesViewModel> viewModels = this.shoppingCartRepository.findAllByUser(getUserEntity()).stream()
                .map(shoppingCartEntity -> {
                    ShoppingCartGamesViewModel viewModel = new ShoppingCartGamesViewModel();

                    viewModel.setName(shoppingCartEntity.getGames().getName());
                    viewModel.setImageUrl(shoppingCartEntity.getGames().getImageUrl());
                    viewModel.setPrice(shoppingCartEntity.getGames().getPrice());

                    return viewModel;
                }).collect(Collectors.toList());

        return viewModels;
    }

    @Override
    public BigDecimal totalPriceOfAllGames() {
        List<ShoppingCartEntity> allByUser = this.shoppingCartRepository.findAllByUser(getUserEntity());

        int result = 0;
        for (ShoppingCartEntity shoppingCartEntity : allByUser) {
           result += shoppingCartEntity.getGames().getPrice().intValue();
        }

        return BigDecimal.valueOf(result);
    }
}
