package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.services.StoreAddGameServiceModel;
import bg.softuni.gamingstore.models.views.GamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import bg.softuni.gamingstore.services.CloudinaryService;
import bg.softuni.gamingstore.services.GameService;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    private Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);

    private List<GameEntity> games;

    public GameServiceImpl(GamesRepository gamesRepository, ShoppingCartRepository shoppingCartRepository, UserService userService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.gamesRepository = gamesRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.games = this.gamesRepository.findAll();
    }

    @Override
    public List<GamesViewModel> getAllGames() {
        List<GameEntity> gameEntities = this.gamesRepository.findAll();
        UserEntity currentUser = this.userService.getUserEntity();

        List<GamesViewModel> result = new ArrayList<>();
        for (GameEntity game : gameEntities) {
            if (!currentUser.getGames().contains(game)){
                GamesViewModel gamesViewModel = this.modelMapper.map(game, GamesViewModel.class);

                result.add(gamesViewModel);
            }
        }

        return result;
    }

    @Override
    public void deleteGame(Long id) {
        this.gamesRepository.deleteById(id);
    }

    @Override
    public long countAllGames() {
        try {
            return this.gamesRepository.count() - this.userService.getUserEntity().getGames().size();
        } catch (Exception e){
            return this.gamesRepository.count();
        }
    }

    @Override
    public void addGamesToUser() {
        UserEntity userEntity = this.userService.getUserEntity();
        List<ShoppingCartEntity> userGames = this.shoppingCartRepository.findAllByUser(userEntity);

        for (ShoppingCartEntity userGame : userGames) {
            userEntity.getGames().add(userGame.getGames());
        }

    }

    @Override
    public void addNewGameToStore(StoreAddGameServiceModel map) throws IOException {
        GameEntity gameEntity = this.modelMapper.map(map, GameEntity.class);
        String imageUrl = null;
        try {
            MultipartFile img = map.getImageUrl();
            imageUrl = this.cloudinaryService.uploadImage(img);
        } catch (Exception ignored){
            
        }

        gameEntity.setImageUrl(imageUrl);

        this.gamesRepository.save(gameEntity);
    }

    @Override
    public List<GameEntity> fillGamesList(List<GameEntity> gameEntities) {
        return this.games = gameEntities;
    }

    @Override
    public GameEntity firstGame() {
        return this.games.get(0);
    }

    @Override
    public GameEntity secondGame() {
        return this.games.get(1);
    }


    @Override
    public List<GameEntity> findAllGames() {
        return this.gamesRepository.findAll();
    }

    @Override
    public GameEntity findByName(String name) {
        return this.gamesRepository.findByName(name);
    }

    @Override
    public GameEntity findById(Long id) {
        return this.gamesRepository.findById(id).get();
    }

    @Scheduled(fixedRate = 60000)
    public void refresh() {
        LOGGER.info("Shuffling games...");
        Collections.shuffle(games);
    }
}
