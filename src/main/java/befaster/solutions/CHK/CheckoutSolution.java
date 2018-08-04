package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;
    private final static int NO_PRODUCTS_VALUE = 0;

    private final static Map<Character, Product> DEFAULT_PRODUCTS =
            listOfEntitiesWithProductIdToIdEntityMap(asList(new Product('A', 50),
                                                            new Product('B', 30),
                                                            new Product('C', 20),
                                                            new Product('D', 15),
                                                            new Product('E', 40)),
                                                     Product::getId);

    private final static Map<Character, DiscountOffer> DEFAULT_DISCOUNT_OFFERS =
            listOfEntitiesWithProductIdToIdEntityMap(asList(new DiscountOffer(DEFAULT_PRODUCTS.get('A'), 3, 130),
                                                            new DiscountOffer(DEFAULT_PRODUCTS.get('A'), 5, 200),
                                                            new DiscountOffer(DEFAULT_PRODUCTS.get('B'), 2, 45)),
                                                     DiscountOffer::getProductId);

    private final static Map<Character, FreebieOffer> DEFAULT_FREEBIE_OFFERS =
            listOfEntitiesWithProductIdToIdEntityMap(singletonList(new FreebieOffer(DEFAULT_PRODUCTS.get('E'), 2, DEFAULT_PRODUCTS.get('B'))),
                                                     FreebieOffer::getProductId);

    private static <T> Map<Character, T> listOfEntitiesWithProductIdToIdEntityMap(List<T> entities, Function<T, Character> idGetter) {
        return entities.stream()
                       .collect(toMap(idGetter, identity()));
    }

    private final Map<Character, Product> products = DEFAULT_PRODUCTS;
    private final Map<Character, DiscountOffer> specialOffers = DEFAULT_DISCOUNT_OFFERS;
    private final Map<Character, FreebieOffer> freebieOffers = DEFAULT_FREEBIE_OFFERS;

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
        int eProductsAmount = (int) products.stream()
                                            .filter(p -> p.getId() == 'E')
                                            .count();

        int amountOfEDiscounts = eProductsAmount / 2;

        return HashMultiset.create(products)
                           .entrySet()
                           .stream()
                           .map(this::calculateProductsValue)
                           .reduce(Integer::sum)
                           .orElse(NO_PRODUCTS_VALUE);
    }

    private int calculateProductsValue(Multiset.Entry<Product> productEntry) {
        final int productOccurrencesCount = productEntry.getCount();
        final Product product = productEntry.getElement();
        final DiscountOffer discountOffer = specialOffers.get(product.getId());

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
