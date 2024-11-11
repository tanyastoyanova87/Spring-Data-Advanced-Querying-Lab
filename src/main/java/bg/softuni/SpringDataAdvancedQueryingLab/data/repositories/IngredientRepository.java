package bg.softuni.SpringDataAdvancedQueryingLab.data.repositories;

import bg.softuni.SpringDataAdvancedQueryingLab.data.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Set<Ingredient> findAllByNameStartingWith(String letter);
    Set<Ingredient> findAllByNameInOrderByPrice(List<String> ingredients);
    @Transactional
    void deleteByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1")
    @Modifying
    @Transactional
    void updateIngredientByPrice();

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1 " +
            "WHERE i.name IN(:names)")
    @Modifying
    @Transactional
    void updateIngredientByPriceNamesIn(List<String> names);

}
