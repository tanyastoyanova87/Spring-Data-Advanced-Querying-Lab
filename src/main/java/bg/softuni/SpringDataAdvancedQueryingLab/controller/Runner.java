package bg.softuni.SpringDataAdvancedQueryingLab.controller;

import bg.softuni.SpringDataAdvancedQueryingLab.data.entities.Size;
import bg.softuni.SpringDataAdvancedQueryingLab.data.repositories.IngredientRepository;
import bg.softuni.SpringDataAdvancedQueryingLab.data.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public Runner(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) {
        this.shampooRepository.findShampoosBySizeOrderById(Size.MEDIUM)
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n",
                        shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));

        this.shampooRepository.findShampoosBySizeOrLabelIdOrderByPrice(Size.MEDIUM, 10)
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n",
                        shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));

        this.shampooRepository.findShampoosByPriceAfterOrderByPriceDesc(BigDecimal.valueOf(5))
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n",
                shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));

        this.ingredientRepository.findAllByNameStartingWith("M")
                .forEach(ingredient -> System.out.println(ingredient.getName()));

        this.ingredientRepository.findAllByNameInOrderByPrice(List.of("Lavender", "Herbs", "Apple"))
                .forEach(ingredient -> System.out.println(ingredient.getName()));

        System.out.println(this.shampooRepository
                           .findAllShampooSizeByPriceLessThan(BigDecimal.valueOf(8.5))
                                .size());

        this.shampooRepository.findByIngredientsNameIn(List.of("Berry", "Mineral-Collagen"))
                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

        this.shampooRepository.findByIngredientsCountLessThan(2)
                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

        this.ingredientRepository.deleteByName("Apple");

        this.ingredientRepository.updateIngredientByPrice();

        this.ingredientRepository
                .updateIngredientByPriceNamesIn(List.of("Apple", "Nettle", "Macadamia Oil"));
    }
}
