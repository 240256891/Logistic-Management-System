package za.ac.cput.logisticmanagementsystem.service;

import za.ac.cput.logistics.domain.Inventory;
import za.ac.cput.logistics.repository.InventoryRepository;
import za.ac.cput.logistics.repository.InventoryRepositoryImpl;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository =
            new InventoryRepositoryImpl();

    @Override
    public Inventory addStock(Inventory inventory) {

        return repository.create(inventory);
    }

    @Override
    public Inventory deductStock(String inventoryId,int quantity) {

        Inventory inventory = repository.read(inventoryId);

        if(inventory == null)
            return null;

        if(inventory.getQuantityAvailable() < quantity)
            throw new IllegalArgumentException("Insufficient Stock");

        Inventory updated = new Inventory.Builder()
                .setInventoryId(inventory.getInventoryId())
                .setItemName(inventory.getItemName())
                .setSku(inventory.getSku())
                .setCompanyId(inventory.getCompanyId())
                .setUnitWeight(inventory.getUnitWeight())
                .setQuantityAvailable(inventory.getQuantityAvailable()-quantity)
                .build();

        repository.update(updated);

        return updated;
    }

    @Override
    public boolean checkAvailability(String inventoryId,int quantity){

        Inventory inventory = repository.read(inventoryId);

        return inventory != null &&
                inventory.getQuantityAvailable() >= quantity;
    }

    @Override
    public Inventory read(String inventoryId){
        return repository.read(inventoryId);
    }

    @Override
    public List<Inventory> getAll(){
        return repository.getAll();
    }
}
