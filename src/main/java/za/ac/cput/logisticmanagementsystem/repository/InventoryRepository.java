package za.ac.cput.logisticmanagementsystem.repository;

import za.ac.cput.logistics.domain.Inventory;

import java.util.List;

public interface InventoryRepository {

    Inventory create(Inventory inventory);

    Inventory read(String inventoryId);

    Inventory update(Inventory inventory);

    boolean delete(String inventoryId);

    List<Inventory> getAll();
}
