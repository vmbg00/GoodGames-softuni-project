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
import java.util.stream.Collectors;

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

        for (ShoppingCartEntity userGame : userGames) {
            userEntity.getGames().add(userGame.getGames());
        }

    }

    @Override
    public void addNewGameToStore(StoreAddGameServiceModel map) throws IOException {
        GameEntity gameEntity = this.modelMapper.map(map, GameEntity.class);

        MultipartFile img = map.getImageUrl();
        String imageUrl = this.cloudinaryService.uploadImage(img);

        gameEntity.setImageUrl(imageUrl);

        this.gamesRepository.save(gameEntity);
    }

    @Override
    public GameEntity firstGame() {
        return games.get(0);
    }

    @Override
    public GameEntity secondGame() {
        return games.get(1);
    }

    @Scheduled(fixedRate = 60000)
    public void refresh() {
        LOGGER.info("Shuffling games...");
        Collections.shuffle(games);
    }
}
