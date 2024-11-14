package tasking.test.tasking.test.controller;

import tasking.test.tasking.test.model.KategoriProduk;
import tasking.test.tasking.test.service.KategoriProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kategori")
public class KategoriProdukController {

    @Autowired
    private KategoriProdukService kategoriProdukService;

    // Create KategoriProduk
    @PostMapping
    public ResponseEntity<KategoriProduk> createKategoriProduk(@RequestBody KategoriProduk kategoriProduk) {
        return new ResponseEntity<>(kategoriProdukService.createKategoriProduk(kategoriProduk), HttpStatus.CREATED);
    }

    // Read KategoriProduk by ID
    @GetMapping("/{id}")
    public ResponseEntity<KategoriProduk> getKategoriProdukById(@PathVariable Long id) {
        Optional<KategoriProduk> kategoriProduk = kategoriProdukService.getKategoriProdukById(id);
        return kategoriProduk.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Read All KategoriProduk
    @GetMapping
    public List<KategoriProduk> getAllKategoriProduk() {
        return kategoriProdukService.getAllKategoriProduk();
    }

    // Update KategoriProduk
    @PutMapping("/{id}")
    public ResponseEntity<KategoriProduk> updateKategoriProduk(@PathVariable Long id, @RequestBody KategoriProduk kategoriProduk) {
        KategoriProduk updatedKategoriProduk = kategoriProdukService.updateKategoriProduk(id, kategoriProduk);
        return updatedKategoriProduk != null ? new ResponseEntity<>(updatedKategoriProduk, HttpStatus.OK)
                                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete KategoriProduk
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategoriProduk(@PathVariable Long id) {
        kategoriProdukService.deleteKategoriProduk(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

