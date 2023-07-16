package id.co.mii.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Result;
import id.co.mii.serverapp.models.Role;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
