package za.ac.cput.logisticmanagementsystem.repository;

import za.ac.cput.logistics.domain.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryRepositoryImpl implements InventoryRepository {

    private final List<Inventory> inventoryList = new ArrayList<>();

    @Override
    public Inventory create(Inventory inventory) {
        inventoryList.add(inventory);
        return inventory;
    }

    @Override
    public Inventory read(String inventoryId) {

        return inventoryList.stream()
                .filter(i -> i.getInventoryId().equals(inventoryId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Inventory update(Inventory inventory) {

        delete(inventory.getInventoryId());

        inventoryList.add(inventory);

        return inventory;
    }

    @Override
    public boolean delete(String inventoryId) {

        return inventoryList.removeIf(i ->
                i.getInventoryId().equals(inventoryId));
    }

    @Override
    public List<Inventory> getAll() {

        return inventoryList;
    }
}
