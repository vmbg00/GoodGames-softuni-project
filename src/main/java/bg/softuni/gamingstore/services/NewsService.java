package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.NewsAddServiceModel;
import bg.softuni.gamingstore.models.views.NewsViewModel;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    List<NewsViewModel> getAllNews();

    void addNewNews(NewsAddServiceModel map) throws IOException;

    List<NewsViewModel> getLatestNews();
}
