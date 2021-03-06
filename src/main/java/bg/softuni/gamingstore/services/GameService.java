package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.views.GamesViewModel;

import java.util.List;

public interface GameService {

    List<GamesViewModel> getAllGames();

    void deleteGame(Long id);

    long countAllGames();
}
