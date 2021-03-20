package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.NewsEntity;
import bg.softuni.gamingstore.models.services.NewsAddServiceModel;
import bg.softuni.gamingstore.models.views.NewsViewModel;
import bg.softuni.gamingstore.repositories.NewsRepository;
import bg.softuni.gamingstore.services.CloudinaryService;
import bg.softuni.gamingstore.services.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<NewsViewModel> getAllNews() {
        return this.newsRepository.findAllByOrderByIdDesc().stream().map(newsEntity -> {
            NewsViewModel viewModel = this.modelMapper.map(newsEntity, NewsViewModel.class);

            viewModel.setUserEntity(newsEntity.getUserEntity());

            return viewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void addNewNews(NewsAddServiceModel newsAddServiceModel) throws IOException {
        NewsEntity newsEntity = this.modelMapper.map(newsAddServiceModel, NewsEntity.class);

        MultipartFile img = newsAddServiceModel.getImage();
        String imageUrl = this.cloudinaryService.uploadImage(img);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String format = formatter.format(LocalDate.now());

        newsEntity.setDate(format);
        newsEntity.setImage(imageUrl);
        newsEntity.setUserEntity(newsEntity.getUserEntity());

        this.newsRepository.save(newsEntity);
    }

    @Override
    public List<NewsViewModel> getLatestNews() {
        List<NewsEntity> allNews = this.newsRepository.findAllByOrderByIdDesc();

        List<NewsViewModel> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                NewsEntity newsEntity = allNews.get(i);
                NewsViewModel viewModel = this.modelMapper.map(newsEntity, NewsViewModel.class);

                viewModel.setUserEntity(newsEntity.getUserEntity());

                result.add(viewModel);
            } catch (Exception e){
                break;
            }
        }

        return result;
    }
}
