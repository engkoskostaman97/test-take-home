package tasking.test.tasking.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tasking.test.tasking.test.model.Transaksi;

public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
}
