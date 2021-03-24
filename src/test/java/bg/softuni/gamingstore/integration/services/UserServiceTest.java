package bg.softuni.gamingstore.integration.services;

import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository mockRepository;

    @Autowired
    private UserService userService;


    @Test
    public void findByNameShouldReturnNull() {
        String name = "name";

        Mockito.when(mockRepository.findByUsername(name))
                .thenReturn(null);

        Optional<UserEntity> byUsername = this.userService.findByUsername(name);

        assertNull(byUsername);

    }

    @Test
    public void findByNameShouldReturnCorrectly() {
        String name = "admin";

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(name);

        Mockito.when(mockRepository.findByUsername(name))
                .thenReturn(Optional.of(userEntity));

        Optional<UserEntity> result = this.userService.findByUsername(name);

        assertEquals(userEntity.getUsername(), result.get().getUsername());
    }

    @Test
    public void getAllShouldReturnCollectionOfServiceModels() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity());

        Mockito.when(mockRepository.findAll())
                .thenReturn(users);

        List<UserEntity> all = this.userService.getAll();

        assertEquals(users.size(), all.size());
    }

    @Test
    public void getIdByUsernameMethod_Should_ReturnCorrectId() {
        String username = "admin";

        UserEntity user = new UserEntity();
        user.setId(Long.parseLong("1"));
        user.setUsername(username);

        Mockito.when(mockRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        Long idByUsername = this.userService.getIdByUsername(username);

        assertEquals(user.getId(), idByUsername);
    }


    @Test
    public void  getIdByUserNameMethod_Should_Return_Null_WhenUserDoesNotExist() {
        String username = "nullname";

        Mockito.when(mockRepository.findByUsername(username))
                .thenReturn(null);

        Long idBySpecialtyName = this.userService.getIdByUsername(username);

        assertNull(idBySpecialtyName);
    }

    @Test
    public void getByAllMethodShouldReturnCollectionOfUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity());

        Mockito.when(mockRepository.findAll())
                .thenReturn(users);

        List<UserEntity> result = this.userService.getAll();

        assertEquals(users.size(), result.size());
    }

    @Test
    public void getByEmailMethodShouldReturnCorrectEntity() {
        String email = "admin@gmail.com";

        UserEntity user = new UserEntity();
        user.setEmail(email);

        Mockito.when(mockRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        UserEntity byEmail = this.userService.getByEmail(email);

        assertEquals(user.getEmail(), byEmail.getEmail());
    }

    @Test
    public void getByEmailShouldReturnNull() {
        String email = "email";

        Mockito.when(mockRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        UserEntity result = this.userService.getByEmail(email);

        assertNull(result);
    }

}
