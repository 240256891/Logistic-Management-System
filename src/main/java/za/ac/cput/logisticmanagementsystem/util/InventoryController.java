package za.ac.cput.logisticmanagementsystem.util;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.logistics.domain.Inventory;
import za.ac.cput.logistics.service.InventoryService;
import za.ac.cput.logistics.service.InventoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service =
            new InventoryServiceImpl();

    @PostMapping("/create")
    public Inventory create(@RequestBody Inventory inventory){
        return service.addStock(inventory);
    }

    @GetMapping("/{id}")
    public Inventory read(@PathVariable String id){
        return service.read(id);
    }

    @GetMapping("/all")
    public List<Inventory> getAll(){
        return service.getAll();
    }

    @PutMapping("/deduct/{id}/{qty}")
    public Inventory deduct(@PathVariable String id,
                            @PathVariable int qty){

        return service.deductStock(id,qty);
    }

    @GetMapping("/available/{id}/{qty}")
    public boolean available(@PathVariable String id,
                             @PathVariable int qty){

        return service.checkAvailability(id,qty);
    }
}