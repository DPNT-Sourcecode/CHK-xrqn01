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
        products.stream()
                .filter(product -> product.getId() == productId)
    }

}
