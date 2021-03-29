package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.models.views.GalleryViewModel;
import bg.softuni.gamingstore.repositories.PicturesRepository;
import bg.softuni.gamingstore.services.CloudinaryService;
import bg.softuni.gamingstore.services.GalleryService;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final PicturesRepository picturesRepository;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public GalleryServiceImpl(PicturesRepository picturesRepository, UserService userService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.picturesRepository = picturesRepository;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(GalleryAddServiceModel galleryAddServiceModel) throws IOException {
        PictureEntity pictureEntity = this.modelMapper.map(galleryAddServiceModel, PictureEntity.class);

        MultipartFile img = galleryAddServiceModel.getImg();
        String imageUrl = this.cloudinaryService.uploadImage(img);

        pictureEntity.setId(galleryAddServiceModel.getId());
        pictureEntity.setUserEntity(this.userService.getUserEntity());
        pictureEntity.setUrl(imageUrl);

        this.picturesRepository.save(pictureEntity);
    }

    @Override
    public List<GalleryViewModel> getAllPics() {
        return this.picturesRepository.findAllByOrderByIdDesc().stream().map(pictureEntity ->
                this.modelMapper.map(pictureEntity, GalleryViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PictureEntity> findAllPictures() {
        return this.picturesRepository.findAll();
    }

    @Override
    public PictureEntity findPictureByTitle(String title) {
        return this.picturesRepository.findPictureEntityByTitle(title);
    }
}
