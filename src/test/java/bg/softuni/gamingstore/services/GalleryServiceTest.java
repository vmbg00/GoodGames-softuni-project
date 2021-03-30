package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.models.views.GalleryViewModel;
import bg.softuni.gamingstore.repositories.PicturesRepository;
import bg.softuni.gamingstore.services.impl.GalleryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void getAllPicturesShouldReturnCorrectCollection(){
        List<GalleryViewModel> viewModels = new ArrayList<>();
        GalleryViewModel galleryViewModel = new GalleryViewModel();
        GalleryViewModel galleryViewModel2 = new GalleryViewModel();
        galleryViewModel.setTitle("Test1");
        galleryViewModel2.setTitle("Test2");

        viewModels.addAll(List.of(galleryViewModel, galleryViewModel2));

        Mockito.when(this.galleryService.getAllPics())
                .thenReturn(viewModels);

        assertEquals(2, viewModels.size());
    }
}
