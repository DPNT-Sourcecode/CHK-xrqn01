package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

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
    private final List<PriceCalculationStrategy> specialOffersByPriority = DEFAULT_SPECIAL_OFFERS;

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
        int amountOfEDiscounts = countProductsWithId(products, 'E') / 2;
        IntStream.range(0, amountOfEDiscounts)
                 .forEach(i -> products.remove(DEFAULT_PRODUCTS.get('B')));

        return HashMultiset.create(products)
                           .entrySet()
                           .stream()
                           .map(this::calculateProductsValue)
                           .reduce(Integer::sum)
                           .orElse(NO_PRODUCTS_VALUE);
    }

    private int countProductsWithId(List<Product> products, char givenId) {
        return (int) products.stream()
                             .map(Product::getId)
                             .filter(id -> id == givenId)
                             .count();
    }

    private int calculateProductsValue(Multiset.Entry<Product> productEntry) {
        final int productOccurrencesCount = productEntry.getCount();
        final Product product = productEntry.getElement();
        final DiscountOffer discountOffer = specialOffersByPriority.get(product.getId());

        if (discountOffer != null) {
            final int productsAmountRequiredForOffer = discountOffer.getProductsAmount();
            final int applyOfferTimes = productOccurrencesCount / productsAmountRequiredForOffer;
            final int applyOfferToProducts = applyOfferTimes * productsAmountRequiredForOffer;
            final int discountPriceTotal = discountOffer.getDiscountPrice() * applyOfferTimes;
            final int nonDiscountedProductsAmount = productOccurrencesCount - applyOfferToProducts;
            final int nonDiscountedPriceTotal = nonDiscountedProductsAmount * product.getPrice();
            return discountPriceTotal + nonDiscountedPriceTotal;
        }
        else {
            return productOccurrencesCount * product.getPrice();
        }
    }
}
