package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.NewsAddServiceModel;
import bg.softuni.gamingstore.models.views.NewsViewModel;
import bg.softuni.gamingstore.repositories.NewsRepository;
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

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;
    @MockBean
    private NewsRepository mockRepository;

    @Test
    public void addingNewsShouldBeSuccessful() throws IOException {
        NewsAddServiceModel serviceModel = new NewsAddServiceModel();
        serviceModel.setTitle("Test1");

        this.newsService.addNewNews(serviceModel);
        List<NewsViewModel> allNews = this.newsService.getAllNews();

        assertEquals(1, allNews.size());
    }

    @Test
    public void gettingLatestNewsMethodShouldReturnCorrectCollection() {
        List<NewsViewModel> viewModels = new ArrayList<>();
        NewsViewModel newsViewModel1 = new NewsViewModel().setTitle("123");
        NewsViewModel newsViewModel2 = new NewsViewModel().setTitle("456");
        NewsViewModel newsViewModel3 = new NewsViewModel().setTitle("123123");

        viewModels.addAll(List.of(newsViewModel1, newsViewModel3, newsViewModel2));

        Mockito.when(this.newsService.getLatestNews())
                .thenReturn(viewModels);

        assertEquals(3, viewModels.size());
    }
}
