package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.views.NewsViewModel;
import bg.softuni.gamingstore.repositories.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;
    @MockBean
    private NewsRepository mockRepository;


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

    @Test
    public void getAllNewsMethodShouldReturnCorrectCollection(){
        List<NewsViewModel> viewModels = new ArrayList<>();
        NewsViewModel newsViewModel1 = new NewsViewModel().setTitle("123");
        NewsViewModel newsViewModel2 = new NewsViewModel().setTitle("456");
        NewsViewModel newsViewModel3 = new NewsViewModel().setTitle("asdvb");
        NewsViewModel newsViewModel5 = new NewsViewModel().setTitle("fgfg");
        NewsViewModel newsViewModel6 = new NewsViewModel().setTitle("dasads");

        viewModels.addAll(List.of(newsViewModel1, newsViewModel3, newsViewModel2, newsViewModel5, newsViewModel6));

        Mockito.when(this.newsService.getAllNews())
                .thenReturn(viewModels);

        assertEquals(5, viewModels.size());
    }

    @Test
    public void getAllNewsMethodShouldReturnNull(){
        Mockito.when(this.mockRepository.findAll())
                .thenReturn(null);

        List<NewsViewModel> allNews = this.newsService.getAllNews();

        assertEquals(0, allNews.size());
    }
}
