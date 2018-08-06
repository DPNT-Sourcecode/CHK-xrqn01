package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset.Entry;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
final class GroupDiscountPriceCalculationStrategy implements PriceCalculationStrategy {

    private final List<Character> applicableProducts;
    private final int requiredProductsAmount;
    private final int discountedPrice;

    @Override
    public boolean isApplicableTo(Product product) {
        return applicableProducts.contains(product.getId());
    }

    @Override
    public PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products) {
        if (products == null) {
            return PriceCalculationResult.nothingCalculated(HashMultiset.create());
        }

        final List<Entry<Product>> collect = products.entrySet()
                                                     .stream()
                                                     .filter(productEntry -> isApplicableTo(productEntry.getElement()))
                                                     .sorted(comparingInt(firstEntry -> firstEntry.getElement()
                                                                                                  .getPrice()))
                                                     .collect(toList());
    }

    @Override
    public String toString() {
        String applicableProductsString = applicableProducts.stream()
                                                            .map(Object::toString)
                                                            .collect(Collectors.joining(","));
        return "buy any " + requiredProductsAmount + " of (" + applicableProductsString + ") for " + discountedPrice;
    }

}
