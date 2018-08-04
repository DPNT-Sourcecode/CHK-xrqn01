package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;

    private final Map<Character, Product> products;
    private final Map<Character, SpecialOffer> specialOffers;

    public CheckoutSolution() {
        this.products = ImmutableMap.<Character, Product>builder().put('A', new Product('A', 50))
                                                                  .put('B', new Product('B', 50))
                                                                  .put('C', new Product('C', 20))
                                                                  .put('D', new Product('D', 15))
                                                                  .build();
        this.specialOffers = ImmutableMap.<Character, SpecialOffer>builder().put('A', new SpecialOffer(products.get('A'), 3, 130))
                                                                            .put('B', new SpecialOffer(products.get('B'), 2, 45))
                                                                            .build();
    }

    public CheckoutSolution(List<Product> products, List<SpecialOffer> specialOffers) {
        this.products = collectProductsListToMapById(products);
        this.specialOffers = collectSpecialOffersToMapByProductId(specialOffers);
    }

    private Map<Character, Product> collectProductsListToMapById(List<Product> products) {
        return products.stream()
                       .collect(toMap(Product::getId, identity()));
    }

    private Map<Character, SpecialOffer> collectSpecialOffersToMapByProductId(List<SpecialOffer> specialOffers) {
        return specialOffers.stream()
                            .collect(toMap(SpecialOffer::getProductId, identity()));
    }

    public Integer checkout(String skus) {
        return Optional.ofNullable(skus)
                       .map(s -> skusStringToListOfProducts(s))
                       .filter(this::areProductsValid)
                       .map(this::calculatePriceOfSortedProducts)
                       .orElse(INVALID_PRICE_VALUE);
    }

    private List<Product> skusStringToListOfProducts(String s) {
        return s.chars()
                   .mapToObj(id -> products.get((char) id))
                   .collect(toList());
    }

    private boolean areProductsValid(List<Product> productsList) {
        return !productsList.contains(null);
    }

    private int calculatePriceOfSortedProducts(List<Product> products) {
        return HashMultiset.create(products)
                           .entrySet()
                           .stream()
                           .map(this::calculateProductsValue)
                           .reduce(Integer::sum)
                           .orElse(INVALID_PRICE_VALUE);
    }

    private int calculateProductsValue(Multiset.Entry<Product> productEntry) {
        final int productOccurrencesCount = productEntry.getCount();
        final Product product = productEntry.getElement();
        final SpecialOffer specialOffer = specialOffers.get(product.getId());

        if (specialOffer != null) {
            final int productsAmountRequiredForOffer = specialOffer.getProductsAmount();
            final int applyOfferTimes = productOccurrencesCount / productsAmountRequiredForOffer;
            final int applyOfferToProducts = applyOfferTimes * productsAmountRequiredForOffer;
            return (specialOffer.getDiscountPrice() * applyOfferTimes) + ((productOccurrencesCount - applyOfferToProducts) * product.getPrice());
        }
        else {
            return productOccurrencesCount * product.getPrice();
        }
    }
}
