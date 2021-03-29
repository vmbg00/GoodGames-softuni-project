package bg.softuni.gamingstore.services.impl;

import bg.softuni.gamingstore.models.entities.BillingHistoryEntity;
import bg.softuni.gamingstore.models.services.BillingHistoryServiceModel;
import bg.softuni.gamingstore.models.views.BillingHistoryViewModel;
import bg.softuni.gamingstore.repositories.BillingHistoryRepository;
import bg.softuni.gamingstore.repositories.UserRepository;
import bg.softuni.gamingstore.services.BillingHistoryService;
import bg.softuni.gamingstore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BillingHistoryServiceImpl implements BillingHistoryService {

    private final ModelMapper modelMapper;
    private final BillingHistoryRepository billingHistoryRepository;
    private final UserService userService;

    public BillingHistoryServiceImpl(ModelMapper modelMapper, BillingHistoryRepository billingHistoryRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.billingHistoryRepository = billingHistoryRepository;
        this.userService = userService;
    }

    @Override
    public void addToHistory(BillingHistoryServiceModel billing) {
        BillingHistoryEntity billingHistoryEntity = this.modelMapper.map(billing, BillingHistoryEntity.class);
        billingHistoryEntity.setUserEntity(this.userService.getUserEntity().getUsername());

        this.billingHistoryRepository.save(billingHistoryEntity);
    }

    @Override
    public List<BillingHistoryViewModel> getAllBillingHistories() {
        return this.billingHistoryRepository.findAll().stream().map(billingHistoryEntity -> {
            BillingHistoryViewModel viewModel = this.modelMapper.map(billingHistoryEntity, BillingHistoryViewModel.class);

            viewModel.setUser(billingHistoryEntity.getUserEntity());

            return viewModel;
        }).collect(Collectors.toList());
    }
}
