package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;
    private final static int NO_PRODUCTS_VALUE = 0;

    private final static Map<Character, Product> defaultProducts =
            ImmutableMap.<Character, Product>builder().put('A', new Product('A', 50))
                                                      .put('B', new Product('B', 30))
                                                      .put('C', new Product('C', 20))
                                                      .put('D', new Product('D', 15))
                                                      .build();
    private final static Map<Character, SpecialOffer> defaultSpecialOffers =
            ImmutableMap.<Character, SpecialOffer>builder().put('A', new SpecialOffer(defaultProducts.get('A'), 3, 130))
                                                           .put('B', new SpecialOffer(defaultProducts.get('B'), 2, 45))
                                                           .build();

    private final Map<Character, Product> products;
    private final Map<Character, SpecialOffer> specialOffers;

    public CheckoutSolution() {
        this(defaultProducts, defaultSpecialOffers);
    }

    public CheckoutSolution(Map<Character, Product> products, Map<Character, SpecialOffer> specialOffers) {
        this.products = products;
        this.specialOffers = specialOffers;
    }

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
        final SpecialOffer specialOffer = specialOffers.get(product.getId());

        if (specialOffer != null) {
            final int productsAmountRequiredForOffer = specialOffer.getProductsAmount();
            final int applyOfferTimes = productOccurrencesCount / productsAmountRequiredForOffer;
            final int applyOfferToProducts = applyOfferTimes * productsAmountRequiredForOffer;
            final int discountPriceTotal = specialOffer.getDiscountPrice() * applyOfferTimes;
            final int nonDiscountedProductsAmount = productOccurrencesCount - applyOfferToProducts;
            final int nonDiscountedPriceTotal = nonDiscountedProductsAmount * product.getPrice();
            return discountPriceTotal + nonDiscountedPriceTotal;
        }
        else {
            return productOccurrencesCount * product.getPrice();
        }
    }
}
