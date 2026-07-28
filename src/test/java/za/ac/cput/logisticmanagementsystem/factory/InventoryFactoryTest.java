package za.ac.cput.logisticmanagementsystem.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.logisticmanagementsystem.domain.Inventory;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {

    @Test
    void createInventory() {

        Inventory inventory = InventoryFactory.createInventory(
                "Laptop",
                "SKU001",
                50,
                2.5,
                "COMP001"
        );

        assertNotNull(inventory);
        assertNotNull(inventory.getInventoryId());

        assertEquals("Laptop", inventory.getItemName());
        assertEquals("SKU001", inventory.getSku());
        assertEquals(50, inventory.getQuantityAvailable());
        assertEquals(2.5, inventory.getUnitWeight());
        assertEquals("COMP001", inventory.getCompanyId());

        System.out.println(inventory);
    }

    @Test
    void createInventoryWithDifferentValues() {

        Inventory inventory = InventoryFactory.createInventory(
                "Monitor",
                "SKU100",
                20,
                4.8,
                "COMP002"
        );

        assertNotNull(inventory);

        assertEquals("Monitor", inventory.getItemName());
        assertEquals("SKU100", inventory.getSku());
        assertEquals(20, inventory.getQuantityAvailable());
        assertEquals(4.8, inventory.getUnitWeight());
        assertEquals("COMP002", inventory.getCompanyId());
    }

    @Test
    void inventoryIdShouldBeGenerated() {

        Inventory inventory = InventoryFactory.createInventory(
                "Keyboard",
                "KEY123",
                15,
                0.8,
                "COMP003"
        );

        assertNotNull(inventory.getInventoryId());
        assertFalse(inventory.getInventoryId().isEmpty());
    }

    @Test
    void createMultipleInventoryObjects() {

        Inventory first = InventoryFactory.createInventory(
                "Mouse",
                "MS001",
                100,
                0.5,
                "COMP001"
        );

        Inventory second = InventoryFactory.createInventory(
                "Printer",
                "PR100",
                10,
                8.2,
                "COMP001"
        );

        assertNotNull(first);
        assertNotNull(second);

        assertNotEquals(first.getInventoryId(), second.getInventoryId());
    }

    @Test
    void quantityShouldBeCorrect() {

        Inventory inventory = InventoryFactory.createInventory(
                "Desk",
                "DSK500",
                75,
                12.0,
                "COMP010"
        );

        assertTrue(inventory.getQuantityAvailable() > 0);
    }
}
