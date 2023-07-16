package id.co.mii.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    public Optional<Parameter> findByNotes(String notes);
}
