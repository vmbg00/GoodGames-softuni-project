package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.ShoppingCartEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.repositories.ShoppingCartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShoppingCartServiceTest {

    @MockBean
    private ShoppingCartRepository mockRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    public void getAllGamesInCartShouldReturnCorrectEntity(){
        List<ShoppingCartEntity> shoppingCartEntities = new ArrayList<>();
        shoppingCartEntities.add(new ShoppingCartEntity());

        Mockito.when(this.mockRepository.findAll())
                .thenReturn(shoppingCartEntities);

        List<ShoppingCartEntity> allGames = this.shoppingCartService.findAllGames();

        assertEquals(allGames, shoppingCartEntities);
    }

    @Test
    public void getAllGamesInCartShouldReturnNull(){
        Mockito.when(this.mockRepository.findAll())
                .thenReturn(null);

        List<ShoppingCartEntity> allGames = this.shoppingCartService.findAllGames();

        assertNull(allGames);
    }

    @Test
    public void getAllCartsByGamesShouldReturnCorrectCollection(){
        String name = "Glory hold";

        List<ShoppingCartEntity> shoppingCartEntities = new ArrayList<>();

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setGames(new GameEntity().setName(name));
        shoppingCartEntities.add(shoppingCartEntity);

        Mockito.when(this.mockRepository.getShoppingCartEntityByGames_Name(name))
                .thenReturn(shoppingCartEntities);

        List<ShoppingCartEntity> allCartsWithGame = this.shoppingCartService.getAllCartsWithGame(name);

        assertEquals(allCartsWithGame, shoppingCartEntities);
    }

    @Test
    public void getAllCartsByGamesShouldReturnNull(){
        String name = "asdasdasd";

        Mockito.when(this.mockRepository.getShoppingCartEntityByGames_Name(name))
                .thenReturn(null);

        List<ShoppingCartEntity> allCartsWithGame = this.shoppingCartService.getAllCartsWithGame(name);

        assertNull(allCartsWithGame);
    }

    @Test
    public void getAllCartsByUserShouldReturnCorrectCollection(){
        String name = "admin";

        List<ShoppingCartEntity> shoppingCartEntities = new ArrayList<>();

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        shoppingCartEntity.setUser(new UserEntity().setUsername(name));
        shoppingCartEntities.add(shoppingCartEntity);

        Mockito.when(this.mockRepository.getShoppingCartEntityByUser_Username(name))
                .thenReturn(shoppingCartEntities);

        List<ShoppingCartEntity> allCartsWithGame = this.shoppingCartService.getAllCartsWithUsername(name);

        assertEquals(allCartsWithGame, shoppingCartEntities);
    }

    @Test
    public void getAllCartsByUserShouldReturnNull(){
        String name = "asdasdasd";

        Mockito.when(this.mockRepository.getShoppingCartEntityByUser_Username(name))
                .thenReturn(null);

        List<ShoppingCartEntity> allCartsWithGame = this.shoppingCartService.getAllCartsWithUsername(name);

        assertNull(allCartsWithGame);
    }

    @Test
    public void checkIfGameIsInCartShouldReturnTrue(){
        String name = "Glory hold";

        List<ShoppingCartEntity> shoppingCartEntities = new ArrayList<>();

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();

        GameEntity gameEntity = new GameEntity().setName(name);
        gameEntity.setId(Long.parseLong("1"));

        shoppingCartEntity.setGames(gameEntity);
        shoppingCartEntity.setId(Long.parseLong("1"));

        UserEntity userEntity = new UserEntity().setUsername("Tester");
        userEntity.setId(Long.parseLong("1"));
        userEntity.setGames(List.of(gameEntity));

        shoppingCartEntity.setUser(userEntity);

        shoppingCartEntities.add(shoppingCartEntity);

        Mockito.when(this.mockRepository.getShoppingCartEntityByGames_Name(name))
                .thenReturn(shoppingCartEntities);

        Mockito.when(this.mockRepository.save(any(ShoppingCartEntity.class)))
                .thenReturn(shoppingCartEntity);

        ShoppingCartEntity shoppingCartEntity1 = shoppingCartEntities.get(0);
        boolean isTrue = this.shoppingCartService.checkIfGameIsAlreadyInCart(shoppingCartEntity1.getGames().getId(), userEntity);

        assertTrue(isTrue);
    }

}
