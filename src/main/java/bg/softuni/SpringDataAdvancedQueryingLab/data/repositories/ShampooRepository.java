package bg.softuni.springdataadvancedqueryinglab.repositories;

import bg.softuni.springdataadvancedqueryinglab.entities.Shampoo;
import bg.softuni.springdataadvancedqueryinglab.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    Set<Shampoo> findShampoosBySizeOrderById(Size size);
    Set<Shampoo> findShampoosBySizeOrLabelIdOrderByPrice(Size size, long id);
    Set<Shampoo> findShampoosByPriceAfterOrderByPriceDesc(BigDecimal price);
    Set<Shampoo> findAllShampooSizeByPriceLessThan(BigDecimal price);
    Set<Shampoo> findByIngredientsNameIn(List<String> ingredients);

    @Query("SELECT s FROM Shampoo AS s " +
            "WHERE SIZE(s.ingredients) < :count")
    Set<Shampoo> findByIngredientsCountLessThan(int count);
}
