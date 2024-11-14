package tasking.test.tasking.test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tasking.test.tasking.test.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}

