package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset.Entry;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
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

        final Comparator<Entry<Product>> comparator = comparingInt(e -> e.getElement()
                                                                         .getPrice());
        final List<Entry<Product>> applicableProductsByPrice = products.entrySet()
                                                                       .stream()
                                                                       .filter(productEntry -> isApplicableTo(productEntry.getElement()))
                                                                       .sorted(comparator.reversed())
                                                                       .collect(toList());

        final int applicableProductsAmount = applicableProductsByPrice.stream()
                                                                      .map(Entry::getCount)
                                                                      .reduce(Integer::sum)
                                                                      .orElse(0);

        final int applicableDiscountAmount = applicableProductsAmount / requiredProductsAmount;
        final int discountPrice = applicableDiscountAmount * discountedPrice;

        final HashMultiset<Product> result = HashMultiset.create(products);

        int toRemoveProductsAmount = applicableDiscountAmount * requiredProductsAmount;
        for (Entry<Product> entry : applicableProductsByPrice) {
            int newCount = entry.getCount();
            if (newCount - toRemoveProductsAmount < 0) {
                toRemoveProductsAmount -= newCount;
                newCount = 0;
            }
            else {
                newCount -= toRemoveProductsAmount;
                toRemoveProductsAmount = 0;
            }

            Product product = entry.getElement();
            result.setCount(product, newCount);

            if (toRemoveProductsAmount == 0) {
                break;
            }
        }

        return new PriceCalculationResult(result, discountPrice);
    }

    @Override
    public String toString() {
        String applicableProductsString = applicableProducts.stream()
                                                            .map(Object::toString)
                                                            .collect(Collectors.joining(","));
        return "buy any " + requiredProductsAmount + " of (" + applicableProductsString + ") for " + discountedPrice;
    }

}
