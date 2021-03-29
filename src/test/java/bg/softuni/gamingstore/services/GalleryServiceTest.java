package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.repositories.PicturesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GalleryServiceTest {

    @MockBean
    private PicturesRepository mockRepository;

    @Autowired
    private GalleryService galleryService;

    @Test
    public void findAllPicturesShouldReturnCorrectCollection(){
        List<PictureEntity> pictures = new ArrayList<>();
        pictures.add(new PictureEntity());

        Mockito.when(this.mockRepository.findAll())
                .thenReturn(pictures);

        List<PictureEntity> all = this.galleryService.findAllPictures();

        assertEquals(pictures, all);
    }

    @Test
    public void findAllPicturesShouldReturnNull(){
        Mockito.when(this.mockRepository.findAll())
                .thenReturn(null);

        List<PictureEntity> all = this.galleryService.findAllPictures();

        assertNull(all);
    }

    @Test
    public void getAPictureByTitleShouldReturnCorrectEntity(){
        String title = "Alabala";

        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setTitle(title);

        Mockito.when(this.mockRepository.findPictureEntityByTitle(title))
                .thenReturn(pictureEntity);

        PictureEntity actualPicture = this.galleryService.findPictureByTitle(title);

        assertEquals(actualPicture, pictureEntity);
    }

    @Test
    public void addingAGameShouldBeSuccessful() throws IOException {
        GalleryAddServiceModel serviceModel = new GalleryAddServiceModel();
        serviceModel.setTitle("Test1");

        this.galleryService.add(serviceModel);
        List<PictureEntity> all = this.mockRepository.findAll();

        assertEquals(1, all.size());
    }
}
