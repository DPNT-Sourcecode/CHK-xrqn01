package befaster.solutions.CHK;

import befaster.solutions.CHK.PriceCalculationStrategy.PriceCalculationResult;
import com.google.common.collect.HashMultiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;
    private final static int NO_PRODUCTS_VALUE = 0;

    private final static Map<Character, Product> DEFAULT_PRODUCTS = listOfEntitiesWithProductIdToIdEntityMap(asList(new Product('A', 50),
                                                                                                                    new Product('B', 30),
                                                                                                                    new Product('C', 20),
                                                                                                                    new Product('D', 15),
                                                                                                                    new Product('E', 40)),
                                                                                                             Product::getId);

    private final static List<PriceCalculationStrategy> DEFAULT_SPECIAL_OFFERS = asList(FreebiePriceCalculationStrategy.builder()
                                                                                                                       .productId('E')
                                                                                                                       .requiredProductsAmount(2)
                                                                                                                       .freeProductId('B')
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
                                                                                                                                        .build()
                                                                                       );

    private static <T> Map<Character, T> listOfEntitiesWithProductIdToIdEntityMap(List<T> entities, Function<T, Character> idGetter) {
        return entities.stream()
                       .collect(toMap(idGetter, identity()));
    }

    private final Map<Character, Product> products = DEFAULT_PRODUCTS;
    private final List<PriceCalculationStrategy> priceCalculationStrategiesByPriority = DEFAULT_SPECIAL_OFFERS;

    public Integer checkout(String skus) {
        return Optional.ofNullable(skus)
                       .map(this::skusStringToListOfProducts)
                       .filter(this::areProductsValid)
                       .map(this::calculatePriceOfProducts)
                       .orElse(INVALID_PRICE_VALUE);
    }

    private List<Product> skusStringToListOfProducts(String skus) {
        return skus.chars()
                   .mapToObj(id -> products.get((char) id))
                   .collect(toList());
    }

    private boolean areProductsValid(List<Product> productsList) {
        return !productsList.contains(null);
    }

    private int calculatePriceOfProducts(List<Product> products) {
        HashMultiset<Product> productsHashMultiSet = HashMultiset.create(products);
        int price = NO_PRODUCTS_VALUE;
        for (PriceCalculationStrategy strategy : priceCalculationStrategiesByPriority) {
            final PriceCalculationResult result = strategy.applySpecialOfferTo(productsHashMultiSet);
            price += result.getPriceOfDiscountedProducts();
            productsHashMultiSet = result.getRemainingProducts();
        }
        return price;
    }
}
