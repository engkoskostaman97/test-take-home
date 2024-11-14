package tasking.test.tasking.test.repository;

import tasking.test.tasking.test.model.KategoriProduk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriProdukRepository extends JpaRepository<KategoriProduk, Long> {
}
