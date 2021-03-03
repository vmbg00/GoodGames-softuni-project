package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.PictureEntity;
import bg.softuni.gamingstore.models.services.GalleryAddServiceModel;
import bg.softuni.gamingstore.models.views.GalleryViewModel;
import bg.softuni.gamingstore.repositories.PicturesRepository;
import bg.softuni.gamingstore.services.GalleryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final PicturesRepository picturesRepository;
    private final ModelMapper modelMapper;

    public GalleryServiceImpl(PicturesRepository picturesRepository, ModelMapper modelMapper) {
        this.picturesRepository = picturesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(GalleryAddServiceModel galleryAddServiceModel) {
        PictureEntity pictureEntity = this.modelMapper.map(galleryAddServiceModel, PictureEntity.class);

        pictureEntity.setId(galleryAddServiceModel.getId());

        this.picturesRepository.save(pictureEntity);
    }

    @Override
    public List<GalleryViewModel> getAllPics() {
        return this.picturesRepository.findAllByOrderByIdDesc().stream().map(pictureEntity ->
                this.modelMapper.map(pictureEntity, GalleryViewModel.class)).collect(Collectors.toList());
    }
}
