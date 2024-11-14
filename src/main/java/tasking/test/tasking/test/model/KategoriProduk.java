package tasking.test.tasking.test.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "kategori_produk")
public class KategoriProduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaKategori;
    private String deskripsi;
}

