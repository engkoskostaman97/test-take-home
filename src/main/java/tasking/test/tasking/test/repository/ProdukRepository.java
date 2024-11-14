package tasking.test.tasking.test.repository;

import tasking.test.tasking.test.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Long> {
}

