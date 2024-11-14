package tasking.test.tasking.test.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jenisTransaksi; // "STOCK_IN" atau "STOCK_OUT"
    private int jumlah;
    private LocalDateTime tanggalTransaksi = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "produk_id", nullable = false)
    private Produk produk;
}
