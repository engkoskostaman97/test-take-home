package tasking.test.tasking.test.service;

import tasking.test.tasking.test.model.Produk;
import tasking.test.tasking.test.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    // Create Produk
    public Produk createProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    // Read all Produk
    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    // Read Produk by ID
    public Optional<Produk> getProdukById(Long id) {
        return produkRepository.findById(id);
    }

    // Update Produk
    public Produk updateProduk(Long id, Produk updatedProduk) {
        return produkRepository.findById(id).map(produk -> {
            produk.setNamaProduk(updatedProduk.getNamaProduk());
            produk.setDeskripsi(updatedProduk.getDeskripsi());
            produk.setGambar(updatedProduk.getGambar());
            produk.setStok(updatedProduk.getStok());
            produk.setKategoriProduk(updatedProduk.getKategoriProduk());
            return produkRepository.save(produk);
        }).orElse(null);
    }

    // Delete Produk
    public boolean deleteProduk(Long id) {
        if (produkRepository.existsById(id)) {
            produkRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
