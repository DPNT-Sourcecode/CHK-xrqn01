package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;

    private final Map<Character, Product> products;
    private final Map<Product, SpecialOffer> specialOffers;

    public CheckoutSolution() {
        final Product productA = new Product('A', 50);
        final Product productB = new Product('B', 30);
        this.products = collectProductsListToMapById(Arrays.asList(productA,
                                                                   productB,
                                                                   new Product('C', 20),
                                                                   new Product('D', 15)));
        this.specialOffers = Arrays.asList(productA, new SpecialOffer(productA, 3, 130),
                                           productB, new SpecialOffer(productB, 2, 45));
    }

    public CheckoutSolution(List<Product> products, List<SpecialOffer> specialOffers) {
        this.products = collectProductsListToMapById(products);
        this.specialOffers = specialOffers;
    }

    private final Map<Character, Product> collectProductsListToMapById(List<Product> products) {
        return products.stream()
                       .collect(toMap(Product::getId, identity()));
    }

    public Integer checkout(String skus) {
        return Optional.ofNullable(skus)
                       .map(s -> s.chars()
                                  .mapToObj(id -> products.get((char) id))
                                  .collect(toList()))
                       .filter(productsList -> !productsList.contains(null))
                       .map(this::calculatePriceOfSortedProducts)
                       .orElse(INVALID_PRICE_VALUE);
    }

    private int calculatePriceOfSortedProducts(List<Product> products) {
        return HashMultiset.create(products)
                           .entrySet()
                           .stream()
                           .map(this::toProductValue)
                           .reduce(Integer::sum)
                           .orElse(INVALID_PRICE_VALUE);
    }

    private int toProductValue(Multiset.Entry<Product> productEntry) {

    }
}
