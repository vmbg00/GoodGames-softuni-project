package bg.softuni.gamingstore.services;

import bg.softuni.gamingstore.models.services.BillingHistoryServiceModel;

public interface BillingHistoryService {
    void addToHistory(BillingHistoryServiceModel map);
}
