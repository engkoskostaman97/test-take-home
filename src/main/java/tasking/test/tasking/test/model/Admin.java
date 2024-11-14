package tasking.test.tasking.test.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaDepan;
    private String namaBelakang;
    private String email;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String password;
}
