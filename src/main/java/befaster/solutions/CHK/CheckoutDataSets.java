package befaster.solutions.CHK;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CheckoutDataSets {

    final static Map<Character, Product> DEFAULT_PRODUCTS = listOfEntitiesWithProductIdToIdEntityMap(asList(new Product('A', 50),
                                                                                                            new Product('B', 30),
                                                                                                            new Product('C', 20),
                                                                                                            new Product('D', 15),
                                                                                                            new Product('E', 40),
                                                                                                            new Product('F', 10),
                                                                                                            new Product('G', 20),
                                                                                                            new Product('H', 10),
                                                                                                            new Product('I', 35),
                                                                                                            new Product('J', 60),
                                                                                                            new Product('K', 80),
                                                                                                            new Product('L', 90),
                                                                                                            new Product('M', 15),
                                                                                                            new Product('N', 40),
                                                                                                            new Product('O', 10),
                                                                                                            new Product('P', 50),
                                                                                                            new Product('Q', 30),
                                                                                                            new Product('R', 50),
                                                                                                            new Product('S', 30),
                                                                                                            new Product('T', 20),
                                                                                                            new Product('U', 40),
                                                                                                            new Product('V', 50),
                                                                                                            new Product('W', 20),
                                                                                                            new Product('X', 90),
                                                                                                            new Product('Y', 10),
                                                                                                            new Product('Z', 50)),
                                                                                                     Product::getId);

    final static List<PriceCalculationStrategy> DEFAULT_SPECIAL_OFFERS = asList(FreebiePriceCalculationStrategy.builder()
                                                                                                                       .productId('E')
                                                                                                                       .requiredProductsAmount(2)
                                                                                                                       .freeProductId('B')
                                                                                                                       .build(),
                                                                                        FreebiePriceCalculationStrategy.builder()
                                                                                                                       .productId('F')
                                                                                                                       .requiredProductsAmount(3)
                                                                                                                       .freeProductId('F')
                                                                                                                       .build(),
                                                                                        MultipleProductsDiscountPriceCalculationStrategy.builder()
                                                                                                                                        .productId('A')
                                                                                                                                        .productsAmount(5)
                                                                                                                                        .discountPrice(200)
                                                                                                                                        .build(),
                                                                                        MultipleProductsDiscountPriceCalculationStrategy.builder()
                                                                                                                                        .productId('A')
                                                                                                                                        .productsAmount(3)
                                                                                                                                        .discountPrice(130)
                                                                                                                                        .build(),
                                                                                        MultipleProductsDiscountPriceCalculationStrategy.builder()
                                                                                                                                        .productId('B')
                                                                                                                                        .productsAmount(2)
                                                                                                                                        .discountPrice(45)
                                                                                                                                        .build(),
                                                                                        new NonDiscountStrategy()
                                                                                       );

    private static <T> Map<Character, T> listOfEntitiesWithProductIdToIdEntityMap(List<T> entities, Function<T, Character> idGetter) {
        return entities.stream()
                       .collect(toMap(idGetter, identity()));
    }

}