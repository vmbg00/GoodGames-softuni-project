package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.entities.BillingHistoryEntity;
import bg.softuni.gamingstore.models.views.BillingHistoryViewModel;
import bg.softuni.gamingstore.repositories.BillingHistoryRepository;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class BillingServiceTest {

    @MockBean
    private BillingHistoryRepository mockRepository;
    @Autowired
    private BillingHistoryService billingHistoryService;

    @Test
    public void getAllBillingHistoriesShouldReturnCorrectCollection(){
        BillingHistoryViewModel billingHistoryEntity = new BillingHistoryViewModel();
        billingHistoryEntity.setUser("Test1");
        BillingHistoryViewModel billingHistoryEntity2 = new BillingHistoryViewModel();
        billingHistoryEntity.setUser("Test2");
        BillingHistoryViewModel billingHistoryEntity3 = new BillingHistoryViewModel();
        billingHistoryEntity.setUser("Test3");
        BillingHistoryViewModel billingHistoryEntity4 = new BillingHistoryViewModel();
        billingHistoryEntity.setUser("Test4");

        List<BillingHistoryViewModel> viewModelList =
                new ArrayList<>(List.of(billingHistoryEntity, billingHistoryEntity2, billingHistoryEntity3, billingHistoryEntity4));

        Mockito.when(this.billingHistoryService.getAllBillingHistories())
                .thenReturn(viewModelList);

        assertEquals(4, viewModelList.size());
    }
}
