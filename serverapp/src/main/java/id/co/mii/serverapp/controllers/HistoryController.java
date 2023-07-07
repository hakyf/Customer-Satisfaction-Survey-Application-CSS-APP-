package id.co.mii.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.History;
import id.co.mii.serverapp.services.HistoryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private HistoryService historyService;

    @GetMapping
    public List<History> getAll() {
        return historyService.getAll();
    }

    @GetMapping("/{id}")
    public History getById(@PathVariable Long id) {
        return historyService.getById(id);
    }

    @PostMapping
    public History create(@RequestBody History history) {
        return historyService.create(history);
    }

    @PutMapping("/{id}")
    public History update(@PathVariable Long id, @RequestBody History history) {
        return historyService.update(id, history);
    }

    @DeleteMapping("/{id}")
    public History delete(@PathVariable Long id) {
        return historyService.delete(id);
    }

}
