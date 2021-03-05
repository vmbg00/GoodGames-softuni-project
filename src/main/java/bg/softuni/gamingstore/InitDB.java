package bg.softuni.gamingstore;

import bg.softuni.gamingstore.models.entities.GameEntity;
import bg.softuni.gamingstore.models.entities.RoleEntity;
import bg.softuni.gamingstore.models.entities.UserEntity;
import bg.softuni.gamingstore.models.entities.enums.GenreEnum;
import bg.softuni.gamingstore.models.entities.enums.RoleEnums;
import bg.softuni.gamingstore.repositories.GamesRepository;
import bg.softuni.gamingstore.repositories.RolesRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class InitDB implements CommandLineRunner {

    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GamesRepository gamesRepository;

    @Autowired
    public InitDB(RolesRepository rolesRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, GamesRepository gamesRepository) {
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolesRepository.count() == 0){
            RoleEntity admin = new RoleEntity();
            admin.setName(RoleEnums.ADMIN);
            admin.setDescription("Big boss");

            RoleEntity user = new RoleEntity();
            user.setName(RoleEnums.USER);
            user.setDescription("Peasant");

            this.rolesRepository.saveAll(List.of(admin, user));
        }

        if (userRepository.count() == 0){
            UserEntity userEntity = new UserEntity();

            RoleEntity admin = this.rolesRepository.findByName(RoleEnums.ADMIN);
            RoleEntity user = this.rolesRepository.findByName(RoleEnums.USER);

            userEntity
                    .setUsername("admin")
                    .setPassword(this.passwordEncoder.encode("12345"))
                    .setEmail("admin@gmail.com")
                    .setRoles(List.of(admin, user))
                    .setGames(null);

            userRepository.save(userEntity);
        }

        if (gamesRepository.count() == 0){
            GameEntity game1 = new GameEntity();
            GameEntity game2 = new GameEntity();
            GameEntity game3 = new GameEntity();
            GameEntity game4 = new GameEntity();
            GameEntity game5 = new GameEntity();
            GameEntity game6 = new GameEntity();

            game1.setName("IT'S BANANAS");
            game2.setName("PIANO HERO TILES");
            game3.setName("SMASH & CRASH");
            game4.setName("RICH GARDEN");
            game5.setName("Plants vs Zombies");
            game6.setName("Glory Hold");

            game1.setDescription("She clutched the matron by the arm, and forcing her into a chair by the bedside, was about to speak, when looking round, she caught sight of the two old women");
            game2.setDescription("For good, too; though, in consequence of my previous emotions, I was still occasionally seized with a stormy sob. After we had jogged on for some little time");
            game3.setDescription("With what mingled joy and sorrow do I take up the pen to write to my dearest friend! Oh, what a change between to-day and yesterday! Now I am friendless and alone");
            game4.setDescription("A hundred thousand grateful loves to your dear papa and mamma . Is your poor brother recovered of his rack - punch ? Oh, dear!Oh, dear!How men should beware of wicked");
            game5.setDescription("Oh, dear!Oh, dear!How men should beware of wicked");
            game6.setDescription("Is your poor brother recovered of his rack - punch ? ");

            game1.setGenre(GenreEnum.INDIE);
            game2.setGenre(GenreEnum.MMO);
            game3.setGenre(GenreEnum.ACTION);
            game4.setGenre(GenreEnum.ADVENTURE);
            game5.setGenre(GenreEnum.RACING);
            game6.setGenre(GenreEnum.STRATEGY);

            game1.setImageUrl("https://i.pinimg.com/originals/43/8f/66/438f6658e75bfcf2b52cfcab45ce8105.jpg");
            game2.setImageUrl("https://cdn.dribbble.com/users/975607/screenshots/6230100/2.3_ppp_title_ver1_4x.png?compress=1&resize=400x300");
            game3.setImageUrl("https://image.freepik.com/free-vector/modern-logo-game-title-name-with-thunder_23487-186.jpg");
            game4.setImageUrl("https://crucialstudio.com.au/wp-content/uploads/2018/12/game-title-1.jpg");
            game5.setImageUrl("https://i.pinimg.com/236x/45/e4/a7/45e4a7d5e9f6853e8fe05cbce5105517--zombie-party-zombie-app.jpg");
            game6.setImageUrl("https://i.redd.it/3ygdqb46ua451.png");

            game1.setPlatform("XBOX SERIES X");
            game2.setPlatform("PC");
            game3.setPlatform("PC");
            game4.setPlatform("PS5");
            game5.setPlatform("PS5");
            game6.setPlatform("XBOX SERIES X");

            game1.setPrice(BigDecimal.valueOf(14));
            game2.setPrice(BigDecimal.valueOf(20));
            game3.setPrice(BigDecimal.valueOf(23));
            game4.setPrice(BigDecimal.valueOf(32));
            game5.setPrice(BigDecimal.valueOf(28));
            game6.setPrice(BigDecimal.valueOf(11));

            gamesRepository.saveAll(List.of(game1, game2, game3, game4, game5, game6));
        }
    }
}
