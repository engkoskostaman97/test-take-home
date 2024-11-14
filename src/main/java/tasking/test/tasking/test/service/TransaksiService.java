package tasking.test.tasking.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasking.test.tasking.test.model.Produk;
import tasking.test.tasking.test.model.Transaksi;
import tasking.test.tasking.test.repository.ProdukRepository;
import tasking.test.tasking.test.repository.TransaksiRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Transactional
    public Transaksi createTransaksi(Transaksi transaksi) {
        // Pastikan produk dari transaksi memiliki ID yang valid
        Long produkId = transaksi.getProduk().getId();
        Produk produk = produkRepository.findById(produkId)
            .orElseThrow(() -> new IllegalArgumentException("Produk dengan ID " + produkId + " tidak ditemukan"));

        // Atur produk pada transaksi (jika belum di-set)
        transaksi.setProduk(produk);

        // Validasi stok untuk transaksi "STOCK_OUT"
        if ("STOCK_OUT".equals(transaksi.getJenisTransaksi()) && transaksi.getJumlah() > produk.getStok()) {
            throw new IllegalArgumentException("Jumlah transaksi Stock Out melebihi stok yang tersedia");
        }

        // Update stok berdasarkan jenis transaksi
        if ("STOCK_IN".equals(transaksi.getJenisTransaksi())) {
            produk.setStok(produk.getStok() + transaksi.getJumlah());
        } else if ("STOCK_OUT".equals(transaksi.getJenisTransaksi())) {
            produk.setStok(produk.getStok() - transaksi.getJumlah());
        }

        // Simpan perubahan stok produk dan transaksi
        produkRepository.save(produk);
        return transaksiRepository.save(transaksi);
    }

    // Mengambil semua transaksi
    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }

    // Mengambil transaksi berdasarkan ID
    public Optional<Transaksi> getTransaksiById(Long id) {
        return transaksiRepository.findById(id);
    }

    // Memperbarui transaksi berdasarkan ID
    @Transactional
    public Transaksi updateTransaksi(Long id, Transaksi updatedTransaksi) {
        return transaksiRepository.findById(id).map(transaksi -> {
            // Mengambil produk terkait untuk validasi stok
            Long produkId = updatedTransaksi.getProduk().getId();
            Produk produk = produkRepository.findById(produkId)
                .orElseThrow(() -> new IllegalArgumentException("Produk dengan ID " + produkId + " tidak ditemukan"));

            // Validasi stok untuk transaksi "STOCK_OUT"
            if ("STOCK_OUT".equals(updatedTransaksi.getJenisTransaksi()) && updatedTransaksi.getJumlah() > produk.getStok()) {
                throw new IllegalArgumentException("Jumlah transaksi Stock Out melebihi stok yang tersedia");
            }

            // Update jenis transaksi dan jumlah
            transaksi.setJenisTransaksi(updatedTransaksi.getJenisTransaksi());
            transaksi.setJumlah(updatedTransaksi.getJumlah());
            transaksi.setProduk(produk);

            // Sesuaikan stok berdasarkan jenis transaksi
            if ("STOCK_IN".equals(updatedTransaksi.getJenisTransaksi())) {
                produk.setStok(produk.getStok() + updatedTransaksi.getJumlah());
            } else if ("STOCK_OUT".equals(updatedTransaksi.getJenisTransaksi())) {
                produk.setStok(produk.getStok() - updatedTransaksi.getJumlah());
            }

            // Simpan perubahan
            produkRepository.save(produk);
            return transaksiRepository.save(transaksi);
        }).orElseThrow(() -> new IllegalArgumentException("Transaksi dengan ID " + id + " tidak ditemukan"));
    }

    // Menghapus transaksi berdasarkan ID
    public void deleteTransaksi(Long id) {
        Transaksi transaksi = transaksiRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Transaksi dengan ID " + id + " tidak ditemukan"));

        // Mengembalikan stok jika transaksi adalah "STOCK_OUT"
        if ("STOCK_OUT".equals(transaksi.getJenisTransaksi())) {
            Produk produk = transaksi.getProduk();
            produk.setStok(produk.getStok() + transaksi.getJumlah());
            produkRepository.save(produk);
        } else if ("STOCK_IN".equals(transaksi.getJenisTransaksi())) {
            Produk produk = transaksi.getProduk();
            produk.setStok(produk.getStok() - transaksi.getJumlah());
            produkRepository.save(produk);
        }

        // Menghapus transaksi dari repository
        transaksiRepository.delete(transaksi);
    }
}
