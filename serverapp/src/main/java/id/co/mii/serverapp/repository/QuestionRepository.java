package id.co.mii.serverapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.mii.serverapp.models.Question;
import id.co.mii.serverapp.models.Section;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Long> {
        List<Question> findBySection(Section section);
}
