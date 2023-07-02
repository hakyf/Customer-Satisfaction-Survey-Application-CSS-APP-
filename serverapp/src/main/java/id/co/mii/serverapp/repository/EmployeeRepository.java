package id.co.mii.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<Employee> findByName(String name);

    public Optional<Employee> findByNameAndId(String name, Long id);
}
