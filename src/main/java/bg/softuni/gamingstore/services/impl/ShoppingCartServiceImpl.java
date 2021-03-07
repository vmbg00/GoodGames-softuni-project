package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.ShoppingCartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final GamesRepository gamesRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(GamesRepository gamesRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.gamesRepository = gamesRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addToCart(Long id) {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        GameEntity game = this.gamesRepository.findById(id).get();
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserEntity user = this.userRepository.findByUsername(username).get();
        shoppingCartEntity.setGames(game);
        shoppingCartEntity.setUser(user);
        this.shoppingCartRepository.save(shoppingCartEntity);
    }
}
