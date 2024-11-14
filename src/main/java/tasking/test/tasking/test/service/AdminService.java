package tasking.test.tasking.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tasking.test.tasking.test.model.Admin;
import tasking.test.tasking.test.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
      Admin admin = adminRepository.findById(id).orElseThrow();
      admin.setNamaDepan(adminDetails.getNamaDepan());
      admin.setNamaBelakang(adminDetails.getNamaBelakang());
      admin.setTanggalLahir(adminDetails.getTanggalLahir());
      admin.setJenisKelamin(adminDetails.getJenisKelamin());
      return adminRepository.save(admin);
    }

    public Optional<Admin> loginAdmin(String email, String password) {
      return adminRepository.findByEmail(email)
                            .filter(admin -> admin.getPassword().equals(password));
    }
  
  
}

