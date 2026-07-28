package za.ac.cput.logisticmanagementsystem.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.logisticmanagementsystem.domain.Inventory;
import za.ac.cput.logisticmanagementsystem.factory.InventoryFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {

    private InventoryRepository repository;
    private Inventory inventory;

    @BeforeEach
    void setUp() {

        repository = new InventoryRepository();

        inventory = InventoryFactory.createInventory(
                "Laptop",
                "SKU001",
                50,
                2.5,
                "COMP001"
        );
    }

    @Test
    void create() {

        Inventory created = repository.create(inventory);

        assertNotNull(created);
        assertEquals(inventory.getInventoryId(), created.getInventoryId());

        System.out.println("Created Inventory:");
        System.out.println(created);
    }

    @Test
    void read() {

        repository.create(inventory);

        Inventory readInventory = repository.read(inventory.getInventoryId());

        assertNotNull(readInventory);
        assertEquals(inventory.getInventoryId(), readInventory.getInventoryId());
        assertEquals("Laptop", readInventory.getItemName());

        System.out.println("Read Inventory:");
        System.out.println(readInventory);
    }

    @Test
    void update() {

        repository.create(inventory);

        Inventory updated = new Inventory.Builder()
                .setInventoryId(inventory.getInventoryId())
                .setItemName("Gaming Laptop")
                .setSku("SKU001")
                .setQuantityAvailable(75)
                .setUnitWeight(2.8)
                .setCompanyId("COMP001")
                .build();

        repository.update(updated);

        Inventory result = repository.read(updated.getInventoryId());

        assertNotNull(result);
        assertEquals("Gaming Laptop", result.getItemName());
        assertEquals(75, result.getQuantityAvailable());

        System.out.println("Updated Inventory:");
        System.out.println(result);
    }

    @Test
    void delete() {

        repository.create(inventory);

        boolean deleted = repository.delete(inventory.getInventoryId());

        assertTrue(deleted);

        Inventory result = repository.read(inventory.getInventoryId());

        assertNull(result);

        System.out.println("Inventory deleted successfully.");
    }

    @Test
    void getAll() {

        repository.create(inventory);

        Inventory secondInventory = InventoryFactory.createInventory(
                "Monitor",
                "SKU100",
                25,
                4.5,
                "COMP002"
        );

        repository.create(secondInventory);

        List<Inventory> inventoryList = repository.getAll();

        assertNotNull(inventoryList);
        assertEquals(2, inventoryList.size());

        System.out.println("All Inventory:");
        inventoryList.forEach(System.out::println);
    }

    @Test
    void readNonExistingInventory() {

        Inventory result = repository.read("INVALID_ID");

        assertNull(result);
    }

    @Test
    void deleteNonExistingInventory() {

        boolean deleted = repository.delete("INVALID_ID");

        assertFalse(deleted);
    }
}
