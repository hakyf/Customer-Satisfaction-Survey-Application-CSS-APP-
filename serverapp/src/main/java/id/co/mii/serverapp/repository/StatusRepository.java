package id.co.mii.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.mii.serverapp.models.Status;

public interface StatusRepository extends JpaRepository <Status, Long> {}
