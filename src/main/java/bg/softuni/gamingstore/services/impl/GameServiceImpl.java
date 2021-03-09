package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.views.GamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import bg.softuni.gamingstore.services.GameService;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GamesRepository gamesRepository, ShoppingCartRepository shoppingCartRepository, UserService userService, ModelMapper modelMapper) {
        this.gamesRepository = gamesRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GamesViewModel> getAllGames() {
        return this.gamesRepository.findAll().stream().map(gameEntity ->
                this.modelMapper.map(gameEntity, GamesViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteGame(Long id) {
        this.gamesRepository.deleteById(id);
    }

    @Override
    public long countAllGames() {
        return this.gamesRepository.count();
    }

    @Override
    public void addGamesToUser() {
        UserEntity userEntity = this.userService.getUserEntity();
        List<ShoppingCartEntity> userGames = this.shoppingCartRepository.findAllByUser(userEntity);

        List<GameEntity> gameEntities = new ArrayList<>();
        for (ShoppingCartEntity userGame : userGames) {
            gameEntities.add(userGame.getGames());
        }
        userEntity.setGames(gameEntities);

    }
}
