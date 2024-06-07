package ma.gest_dentaire.service;

import ma.gest_dentaire.model.entity.Employer;
import ma.gest_dentaire.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployerService {

    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Optional<Employer> getEmployerById(Integer id) {
        return employerRepository.findById(id);
    }

    public void addEmployer(Employer employer) {
        employerRepository.save(employer);
    }

    public void updateEmployer(Employer employer) {
        employerRepository.save(employer);
    }

    public void deleteEmployer(Integer id) {
        employerRepository.deleteById(id);
    }
}
