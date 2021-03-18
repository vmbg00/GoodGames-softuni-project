package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.BillingHistoryServiceModel;
import bg.softuni.gamingstore.models.views.BillingHistoryViewModel;

import java.util.List;

public interface BillingHistoryService {
    void addToHistory(BillingHistoryServiceModel map);

    List<BillingHistoryViewModel> getAllBillingHistories();
}
