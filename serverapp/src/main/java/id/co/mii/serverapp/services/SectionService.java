package id.co.mii.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.mii.serverapp.models.Section;

import id.co.mii.serverapp.repository.SectionRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class SectionService {
    private SectionRepository sectionRepository;
    public List<Section> getAll(){
        return sectionRepository.findAll();
    }
    public Section getById(Long id){
        return sectionRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Section not found !")
        );
    }
    
    public Section create(Section section){
        return sectionRepository.save(section);
    }

    public Section update(long id, Section section){
        getById(id);
        section.setId(id);
        return sectionRepository.save(section); 
    }

    public Section delete(long id){
        Section section= getById(id);
        sectionRepository.delete(section);
        return section;
    }
    
}
