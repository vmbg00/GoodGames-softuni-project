package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.models.views.GalleryViewModel;

import java.util.List;

public interface GalleryService {
    void add(GalleryAddServiceModel galleryAddServiceModel);

    List<GalleryViewModel> getAllPics();
}
