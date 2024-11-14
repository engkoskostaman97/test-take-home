// File: controller/TransaksiController.java
package tasking.test.tasking.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tasking.test.tasking.test.model.Transaksi;
import tasking.test.tasking.test.service.TransaksiService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @PostMapping
    public ResponseEntity<Transaksi> createTransaksi(@Valid @RequestBody Transaksi transaksi) {
        try {
            return new ResponseEntity<>(transaksiService.createTransaksi(transaksi), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Transaksi> getAllTransaksi() {
        return transaksiService.getAllTransaksi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getTransaksiById(@PathVariable Long id) {
        Optional<Transaksi> transaksi = transaksiService.getTransaksiById(id);
        return transaksi.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaksi> updateTransaksi(@PathVariable Long id, @Valid @RequestBody Transaksi transaksi) {
        Transaksi updatedTransaksi = transaksiService.updateTransaksi(id, transaksi);
        return updatedTransaksi != null ? new ResponseEntity<>(updatedTransaksi, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaksi(@PathVariable Long id) {
        transaksiService.deleteTransaksi(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
