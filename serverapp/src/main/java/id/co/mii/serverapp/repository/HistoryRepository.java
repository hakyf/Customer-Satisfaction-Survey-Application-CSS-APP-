package id.co.mii.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.History;

@Repository
public interface HistoryRepository extends JpaRepository <History, Long> {}
