package befaster.solutions.CHK;

import com.google.common.base.Predicates;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

        products.entrySet()
                .stream()
                .filter(Predicates.compose(this::isApplicableTo, Multiset.Entry<Product>::getElement))
    }

    @Override
    public String toString() {
        String applicableProductsString = applicableProducts.stream()
                                                            .map(Object::toString)
                                                            .collect(Collectors.joining(","));
        return "buy any " + requiredProductsAmount + " of (" + applicableProductsString + ") for " + discountedPrice;
    }

}
