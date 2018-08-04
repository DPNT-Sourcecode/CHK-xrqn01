package befaster.solutions.CHK;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CheckoutSolution {

    private final Map<Character, Product> products;
    private final List<SpecialOffer> specialOffers;

    public CheckoutSolution() {
        final Product productA = new Product('A', 50);
        final Product productB = new Product('B', 30);
        this.products = collectProductsListToMapById(Arrays.asList(productA,
                                                                   productB,
                                                                   new Product('C', 20),
                                                                   new Product('D', 15)));
        this.specialOffers = Arrays.asList(new SpecialOffer(productA, 3, 130),
                                           new SpecialOffer(productB, 2, 45));
    }

    public CheckoutSolution(List<Product> products, List<SpecialOffer> specialOffers) {
        this.products = products;
        this.specialOffers = specialOffers;
    }

    private final Map<Character, Product> collectProductsListToMapById(List<Product> products) {
        return products.stream()
                       .collect(toMap(Product::getId, identity()));
    }

    public Integer checkout(String skus) {
        return Optional.ofNullable(skus)
                       .map(s -> s.chars()
                                  .sorted()
                                  .mapToObj())
                       .orElse(-1);
    }
}
