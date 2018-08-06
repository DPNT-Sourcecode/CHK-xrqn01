package befaster.solutions.CHK;

import befaster.solutions.CHK.PriceCalculationStrategy.PriceCalculationResult;
import com.google.common.collect.HashMultiset;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static befaster.solutions.CHK.CheckoutDataSets.DEFAULT_PRODUCTS;
import static befaster.solutions.CHK.CheckoutDataSets.DEFAULT_PRICE_CALCULATION_STRATEGIES;
import static java.util.stream.Collectors.toList;

public class CheckoutSolution {

    private final static int INVALID_PRICE_VALUE = -1;
    private final static int NO_PRODUCTS_VALUE = 0;
    private final Map<Character, Product> products = DEFAULT_PRODUCTS;
    private final List<PriceCalculationStrategy> priceCalculationStrategiesByPriority = DEFAULT_PRICE_CALCULATION_STRATEGIES;

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
