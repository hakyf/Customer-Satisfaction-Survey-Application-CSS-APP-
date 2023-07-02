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

import id.co.mii.clientapp.model.Section;
import id.co.mii.clientapp.service.SectionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/section")
@AllArgsConstructor
public class SectionRestController {

    private SectionService sectionService;

    @GetMapping
    public List<Section> getAll() {
        return sectionService.getAll();
    }

    @GetMapping("/{id}")
    public Section getById(@PathVariable Long id) {
        return sectionService.getById(id);
    }

    @PostMapping
    public Section create(@RequestBody Section section) {
        return sectionService.create(section);
    }

    @PutMapping("/{id}")
    public Section update(@PathVariable Long id, @RequestBody Section section) {
        return sectionService.update(id, section);
    }

    @DeleteMapping("/{id}")
    public Section delete(@PathVariable Long id) {
        return sectionService.delete(id);
    }

}
