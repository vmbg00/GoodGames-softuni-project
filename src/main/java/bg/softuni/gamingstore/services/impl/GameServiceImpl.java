package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.views.GamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GamesRepository gamesRepository, ModelMapper modelMapper) {
        this.gamesRepository = gamesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GamesViewModel> getAllGames() {
        return this.gamesRepository.findAll().stream().map(gameEntity ->
                this.modelMapper.map(gameEntity, GamesViewModel.class)).collect(Collectors.toList());
    }
}
