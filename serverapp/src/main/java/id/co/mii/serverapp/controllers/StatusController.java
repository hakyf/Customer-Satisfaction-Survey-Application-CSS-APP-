package id.co.mii.serverapp.controllers;

import java.util.List;

// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.mii.serverapp.models.Status;
import id.co.mii.serverapp.services.StatusService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/status")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class StatusController {

    private StatusService StatusService;

    // @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Status> getAll() {
        return StatusService.getAll();
    }

    // @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Status getById(@PathVariable Long id) {
        return StatusService.getById(id);
    }

    // @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Status create(@RequestBody Status status) {
        return StatusService.create(status);
    }

    // @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Status update(@PathVariable Long id, @RequestBody Status status) {
        return StatusService.update(id, status);
    }

    // @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Status delete(@PathVariable Long id) {
        return StatusService.delete(id);
    }
}
