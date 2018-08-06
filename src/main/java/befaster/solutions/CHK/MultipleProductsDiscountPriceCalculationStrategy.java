package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static befaster.solutions.CHK.PriceCalculationStrategy.PriceCalculationResult.nothingCalculated;
import static lombok.AccessLevel.PRIVATE;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
final class MultipleProductsDiscountPriceCalculationStrategy implements PriceCalculationStrategy {

    private final char productId;
    private final int productsAmount;
    private final int discountPrice;

    @Override
    public PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products) {
        final HashMultiset<Product> productsOrEmptyIfNull = Optional.ofNullable(products)
                                                                    .orElse(HashMultiset.create());
        return productsOrEmptyIfNull.entrySet()
                                    .stream()
                                    .filter(productEntry -> productEntry.getElement()
                                                                        .getId() == productId)
                                    .findAny()
                                    .map(productEntry -> applySpecialOfferTo(productsOrEmptyIfNull, productEntry))
                                    .orElse(nothingCalculated(productsOrEmptyIfNull));
    }

    private PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products, Multiset.Entry<Product> productEntry) {
        final int applicableProductsAmount = productEntry.getCount();
        final int applyOfferTimes = applicableProductsAmount / productsAmount;
        final int applyOfferToProducts = applyOfferTimes * productsAmount;
        final int discountPriceTotal = discountPrice * applyOfferTimes;
        final int nonDiscountedProductsAmount = applicableProductsAmount - applyOfferToProducts;

        final HashMultiset<Product> remainingProducts = HashMultiset.create(products);
        remainingProducts.setCount(productEntry.getElement(), nonDiscountedProductsAmount);
        return new PriceCalculationResult(remainingProducts, discountPriceTotal);
    }

    @Override
    public String toString() {
        return String.valueOf(productsAmount) + productId + " for " + discountPrice;
    }

}
