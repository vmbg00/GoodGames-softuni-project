package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.repositories.GamesRepository;
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

    @Test
    public void deletingGameShouldReturnEmpty(){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setName("Alabala");
        gameEntity.setId(Long.parseLong("1"));

        Mockito.when(this.mockRepository.save(gameEntity))
                .thenReturn(gameEntity);

        this.gameService.deleteGame(gameEntity.getId());
        GameEntity byId;
        try {
            byId = this.gameService.findById(Long.parseLong("1"));
        } catch (Exception e){
            byId = null;
        }

        assertNull(byId);
    }

    @Test
    public void findFirstGameAndSecondGameMethodsShouldReturnCorrectEntities(){
        GameEntity game1 = new GameEntity();
        game1.setName("test1");
        game1.setId(Long.parseLong("1"));

        GameEntity game2 = new GameEntity();
        game2.setName("test2");
        game2.setId(Long.parseLong("2"));

        List<GameEntity> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Mockito.when(this.mockRepository.findById(Long.parseLong("1")))
                .thenReturn(java.util.Optional.of(game1));

        Mockito.when(this.mockRepository.findById(Long.parseLong("2")))
                .thenReturn(java.util.Optional.of(game2));

        List<GameEntity> gameEntities = this.gameService.fillGamesList(games);

        GameEntity gameEntity = this.gameService.firstGame();
        GameEntity gameEntity2 = this.gameService.secondGame();

        assertEquals(gameEntities.get(0), gameEntity);
        assertEquals(gameEntities.get(1), gameEntity2);
    }

}
