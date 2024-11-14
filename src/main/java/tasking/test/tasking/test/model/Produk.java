package tasking.test.tasking.test.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "produk")
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaProduk;
    private String deskripsi;
    private String gambar;
    private int stok;

    @ManyToOne(optional = false)
    @JoinColumn(name = "kategori_id", nullable = false)
    private KategoriProduk kategoriProduk;
}
