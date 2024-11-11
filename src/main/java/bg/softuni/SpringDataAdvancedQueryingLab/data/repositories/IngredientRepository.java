package bg.softuni.springdataadvancedqueryinglab.repositories;

import bg.softuni.springdataadvancedqueryinglab.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Set<Ingredient> findAllByNameStartingWith(String letter);
    Set<Ingredient> findAllByNameInOrderByPrice(List<String> ingredients);

}
