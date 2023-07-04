package id.co.mii.clientapp.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.clientapp.model.Status;
import id.co.mii.clientapp.service.StatusService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/status")
@AllArgsConstructor
public class StatusRestController {

    private StatusService statusService;

    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @GetMapping("/{id}")
    public Status getById(@PathVariable Long id) {
        return statusService.getById(id);
    }

    @PostMapping
    public Status create(@RequestBody Status status) {
        return statusService.create(status);
    }

    @PutMapping("/{id}")
    public Status update(@PathVariable Long id, @RequestBody Status status) {
        return statusService.update(id, status);
    }

    @DeleteMapping("/{id}")
    public Status delete(@PathVariable Long id) {
        return statusService.delete(id);
    }

}
