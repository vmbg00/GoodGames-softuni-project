package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.views.GamesViewModel;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GamesRepository gamesRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GamesRepository gamesRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.gamesRepository = gamesRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GamesViewModel> getAllGames() {
        return this.gamesRepository.findAll().stream().map(gameEntity ->
                this.modelMapper.map(gameEntity, GamesViewModel.class)).collect(Collectors.toList());
    }
}
