package za.ac.cput.logisticmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.logisticmanagementsystem.domain.Inventory;
import za.ac.cput.logisticmanagementsystem.service.InventoryService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping({"/api/inventory", "/inventory"})
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Inventory> create(@RequestBody Inventory inventory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStock(inventory));
    }

    @GetMapping("/all")
    public List<Inventory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Inventory read(@PathVariable String id) {
        return service.read(id);
    }

    @PutMapping("/update")
    public Inventory update(@RequestBody Inventory inventory) {
        return service.update(inventory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deduct/{id}/{qty}")
    public Inventory deduct(@PathVariable String id, @PathVariable int qty) {
        return service.deductStock(id, qty);
    }

    @GetMapping("/available/{id}/{qty}")
    public boolean available(@PathVariable String id, @PathVariable int qty) {
        return service.checkAvailability(id, qty);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> badRequest(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> notFound(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> conflict(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message", exception.getMessage()));
    }
}
