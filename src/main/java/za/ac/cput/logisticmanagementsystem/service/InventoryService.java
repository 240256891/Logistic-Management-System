package za.ac.cput.logisticmanagementsystem.service;

import za.ac.cput.logistics.domain.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory addStock(Inventory inventory);

    Inventory deductStock(String inventoryId,int quantity);

    boolean checkAvailability(String inventoryId,int quantity);

    Inventory read(String inventoryId);

    List<Inventory> getAll();
}
