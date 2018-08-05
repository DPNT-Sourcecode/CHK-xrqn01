package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@Builder
@RequiredArgsConstructor(access = PACKAGE)
class MultipleProductsDiscountSpecialOfferStrategy implements SpecialOfferStrategy {

    private final char productId;
    private final int productsAmount;
    private final int discountPrice;

    @Override
    public OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products) {
        final int applicableProductsAmount = (int) products.stream()
                                                           .filter(product -> product.getId() == productId)
                                                           .count();

        final int productsAmountRequiredForOffer = productsAmount;
        final int applyOfferTimes = applicableProductsAmount / productsAmountRequiredForOffer;
        final int applyOfferToProducts = applyOfferTimes * productsAmountRequiredForOffer;
        final int discountPriceTotal = discountPrice * applyOfferTimes;
        final int nonDiscountedProductsAmount = applicableProductsAmount - applyOfferToProducts;
        final int nonDiscountedPriceTotal = nonDiscountedProductsAmount * product.getPrice();
    }

}
