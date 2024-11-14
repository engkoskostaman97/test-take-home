package tasking.test.tasking.test.service;

import tasking.test.tasking.test.model.KategoriProduk;
import tasking.test.tasking.test.repository.KategoriProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategoriProdukService {

    @Autowired
    private KategoriProdukRepository kategoriProdukRepository;

    // Create
    public KategoriProduk createKategoriProduk(KategoriProduk kategoriProduk) {
        return kategoriProdukRepository.save(kategoriProduk);
    }

    // Read (Get by ID)
    public Optional<KategoriProduk> getKategoriProdukById(Long id) {
        return kategoriProdukRepository.findById(id);
    }

    // Read (Get All)
    public List<KategoriProduk> getAllKategoriProduk() {
        return kategoriProdukRepository.findAll();
    }

    // Update
    public KategoriProduk updateKategoriProduk(Long id, KategoriProduk updatedKategoriProduk) {
        return kategoriProdukRepository.findById(id).map(kategori -> {
            kategori.setNamaKategori(updatedKategoriProduk.getNamaKategori());
            kategori.setDeskripsi(updatedKategoriProduk.getDeskripsi());
            return kategoriProdukRepository.save(kategori);
        }).orElse(null);
    }

    // Delete
    public void deleteKategoriProduk(Long id) {
        kategoriProdukRepository.deleteById(id);
    }
}
