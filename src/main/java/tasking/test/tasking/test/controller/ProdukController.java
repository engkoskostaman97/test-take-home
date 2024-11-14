package tasking.test.tasking.test.controller;


import tasking.test.tasking.test.model.Produk;
import tasking.test.tasking.test.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    // Create Produk
    @PostMapping
    public ResponseEntity<Produk> createProduk(@Valid @RequestBody Produk produk) {
        Produk newProduk = produkService.createProduk(produk);
        return new ResponseEntity<>(newProduk, HttpStatus.CREATED);
    }

    // Read All Produk
    @GetMapping
    public ResponseEntity<List<Produk>> getAllProduk() {
        List<Produk> produkList = produkService.getAllProduk();
        return new ResponseEntity<>(produkList, HttpStatus.OK);
    }

    // Read Produk by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable Long id) {
        return produkService.getProdukById(id)
                .map(produk -> new ResponseEntity<>(produk, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update Produk
    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable Long id, @Valid @RequestBody Produk produk) {
        Produk updatedProduk = produkService.updateProduk(id, produk);
        return updatedProduk != null ?
                new ResponseEntity<>(updatedProduk, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Produk
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable Long id) {
        boolean isDeleted = produkService.deleteProduk(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

