package id.co.mii.serverapp.controllers;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.co.mii.serverapp.models.Section;
import id.co.mii.serverapp.services.SectionService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/section")
// @PreAuthorize("hasAnyRole('ADMIN')")
public class SectionController {
private SectionService sectionService;
//  @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Section> getAll(){
        return sectionService.getAll();
    }
// @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public Section getById(@PathVariable Long id){
        return sectionService.getById(id);
    }
// @PreAuthorize("hasAuthority('CREATE_ADMIN','CREATE_USER')")
    @PostMapping
    public Section create(@RequestBody Section section){
        return sectionService.create(section);
    }
    
//   @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Section update(@PathVariable Integer id,@RequestBody Section section){
        return sectionService.update(id, section);
    }
//  @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Section delete(@PathVariable Integer id){
        return sectionService.delete(id);
    }
    
    
}
