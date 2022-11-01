package msherwood.lab6.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import msherwood.lab6.models.Author;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Integer> {

// this will be for the last name
    List<Author> findByLastName(String lastName);
    List<Author> findAllByOrderByLastName();

// this will be for ordering the first name
    List<Author> findByFirstName(String firstName);
    List<Author> findAllByOrderByFirstName();

    
}
