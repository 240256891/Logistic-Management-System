package za.ac.cput.logisticmanagementsystem.factory;


import za.ac.cput.logistics.domain.Inventory;

import java.util.UUID;

public class InventoryFactory {

    public static Inventory createInventory(String itemName,
                                            String sku,
                                            int quantity,
                                            double unitWeight,
                                            String companyId){

        return new Inventory.Builder()
                .setInventoryId(UUID.randomUUID().toString())
                .setItemName(itemName)
                .setSku(sku)
                .setQuantityAvailable(quantity)
                .setUnitWeight(unitWeight)
                .setCompanyId(companyId)
                .build();
    }
}