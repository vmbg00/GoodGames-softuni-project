package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.models.views.GalleryViewModel;

import java.io.IOException;
import java.util.List;

public interface GalleryService {
    void add(GalleryAddServiceModel galleryAddServiceModel) throws IOException;

    List<GalleryViewModel> getAllPics();

    List<PictureEntity> findAllPictures();

    PictureEntity findPictureByTitle(String title);
}
