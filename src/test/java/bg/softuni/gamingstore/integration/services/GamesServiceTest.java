package bg.softuni.gamingstore.integration.services;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.services.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GamesServiceTest {

    @MockBean
    private GamesRepository mockRepository;

    @Autowired
    private GameService gameService;


    @Test
    public void findAllGamesShouldReturnCorrectCollection(){
        List<GameEntity> games = new ArrayList<>();
        games.add(new GameEntity());

        Mockito.when(this.mockRepository.findAll())
                .thenReturn(games);

        List<GameEntity> all = this.gameService.findAllGames();

        assertEquals(games, all);
    }

    @Test
    public void findAllGamesShouldReturnNullCollection(){
        Mockito.when(this.mockRepository.findAll())
                .thenReturn(null);

        List<GameEntity> all = this.gameService.findAllGames();

        assertNull(all);
    }

    @Test
    public void findAGameByNameShouldReturnCorrectEntity(){
        String name = "Glory Hold";

        GameEntity gameEntity = new GameEntity();
        gameEntity.setName(name);

        Mockito.when(this.mockRepository.findByName(name))
                .thenReturn(gameEntity);

        GameEntity byName = this.gameService.findByName(name);

        assertEquals(byName, gameEntity);
    }

    @Test
    public void findAGameByNameShouldReturnNull(){
        String name = "asdasdasd";

        Mockito.when(this.mockRepository.findByName(name))
                .thenReturn(null);

        GameEntity byName = this.gameService.findByName(name);

        assertNull(byName);
    }

    @Test
    public void findGameByIdShouldReturnCorrectEntity(){
        Long id = Long.parseLong("1");

        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(id);

        Mockito.when(this.mockRepository.findById(id))
                .thenReturn(java.util.Optional.of(gameEntity));

        GameEntity byId = this.gameService.findById(id);

        assertEquals(byId, gameEntity);
    }

    @Test
    public void findGameByIdShouldReturnNull(){
        Long id = Long.parseLong("1");

        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(id);

        Mockito.when(this.mockRepository.findById(id))
                .thenReturn(java.util.Optional.of(gameEntity));

        GameEntity byId = this.gameService.findById(id);

        assertEquals(byId, gameEntity);
    }

}
