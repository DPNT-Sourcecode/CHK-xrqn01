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
final class FreebiePriceCalculationStrategy implements PriceCalculationStrategy {

    private final char productId;
    private final int requiredProductsAmount;
    private final char freeProductId;

    @Override
    public PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products) {
        final HashMultiset<Product> productsOrEmptyIfNull = Optional.ofNullable(products)
                                                                    .orElse(HashMultiset.create());
        return productsOrEmptyIfNull.entrySet()
                                    .stream()
                                    .filter(productEntry -> productEntry.getElement()
                                                                        .getId() == productId)
                                    .findAny()
                                    .map(productEntry -> {
                                        return applySpecialOfferTo(productsOrEmptyIfNull, productEntry);
                                    })
                                    .orElse(nothingCalculated(productsOrEmptyIfNull));
    }

    private PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> productsOrEmptyIfNull, Multiset.Entry<Product> productEntry) {
        final int productsAmount = productEntry.getCount();
        final int applicableDiscountsAmount = productsAmount / requiredProductsAmount;

        final HashMultiset<Product> remainingProducts = HashMultiset.create(productsOrEmptyIfNull);

        productsOrEmptyIfNull.entrySet()
                             .stream()
                             .filter(entry -> entry.getElement()
                                                   .getId() == freeProductId)
                             .findAny()
                             .ifPresent(productToRemoveEntry -> {
                                 final int newCount = productToRemoveEntry.getCount() >= applicableDiscountsAmount
                                                      ? productToRemoveEntry.getCount() - applicableDiscountsAmount
                                                      : 0;
                                 remainingProducts.setCount(productToRemoveEntry.getElement(), newCount);
                             });
        return new PriceCalculationResult(remainingProducts, 0);
    }

}
