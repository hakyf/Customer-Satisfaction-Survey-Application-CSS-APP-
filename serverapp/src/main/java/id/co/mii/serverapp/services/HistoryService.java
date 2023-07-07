package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.History;
import id.co.mii.serverapp.repository.HistoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryService {
    private HistoryRepository historyRepository;

    public List<History> getAll() {
        return historyRepository.findAll();
    }

    public History getById(Long id) {
        return historyRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "History not found !"));
    }

    public History create(History history) {
        return historyRepository.save(history);
    }

    public History update(long id, History history) {
        getById(id);
        history.setId(id);
        return historyRepository.save(history);
    }

    public History delete(long id) {
        History history = getById(id);
        historyRepository.delete(history);
        return history;
    }

}
