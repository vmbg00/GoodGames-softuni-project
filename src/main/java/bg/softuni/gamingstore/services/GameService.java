package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.services.StoreAddGameServiceModel;
import bg.softuni.gamingstore.models.views.GamesViewModel;

import java.io.IOException;
import java.util.List;

public interface GameService {

    List<GamesViewModel> getAllGames();

    void deleteGame(Long id);

    long countAllGames();

    void addGamesToUser();

    void addNewGameToStore(StoreAddGameServiceModel map) throws IOException;

    GameEntity firstGame();

    GameEntity secondGame();

    List<GameEntity> findAllGames();

    GameEntity findByName(String name);

    GameEntity findById(Long id);
}
