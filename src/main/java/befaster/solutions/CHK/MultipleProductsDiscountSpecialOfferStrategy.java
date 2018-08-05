package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static befaster.solutions.CHK.SpecialOfferStrategy.OfferApplicationResult.nothingDiscounted;
import static lombok.AccessLevel.PACKAGE;

@Builder
@RequiredArgsConstructor(access = PACKAGE)
class MultipleProductsDiscountSpecialOfferStrategy implements SpecialOfferStrategy {

    private final char productId;
    private final int productsAmount;
    private final int discountPrice;

    @Override
    public OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products) {
        final HashMultiset<Product> productsOrEmptyIfNull = Optional.ofNullable(products)
                                                                    .orElse(HashMultiset.create());
        return productsOrEmptyIfNull.entrySet()
                                    .stream()
                                    .filter(productEntry -> productEntry.getElement()
                                                                        .getId() == productId)
                                    .findAny()
                                    .map(productEntry -> applySpecialOfferTo(productsOrEmptyIfNull, productEntry))
                                    .orElse(nothingDiscounted(productsOrEmptyIfNull));
    }

    private OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products, Multiset.Entry<Product> productEntry) {
        final int applicableProductsAmount = productEntry.getCount();
        final int applyOfferTimes = applicableProductsAmount / productsAmount;
        final int applyOfferToProducts = applyOfferTimes * productsAmount;
        final int discountPriceTotal = discountPrice * applyOfferTimes;
        final int nonDiscountedProductsAmount = applicableProductsAmount - applyOfferToProducts;

        final HashMultiset<Product> remainingProducts = HashMultiset.create(products);
        remainingProducts.setCount(productEntry.getElement(), nonDiscountedProductsAmount);
        return new OfferApplicationResult(remainingProducts, discountPriceTotal);
    }

}
