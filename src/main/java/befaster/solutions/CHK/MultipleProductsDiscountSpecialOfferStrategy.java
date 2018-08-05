package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class MultipleProductsDiscountSpecialOfferStrategy implements SpecialOfferStrategy {

    private final char productId;

    public MultipleProductsDiscountSpecialOfferStrategy(Product product) {
        this.productId = product.getId();
    }

    @Override
    public OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products) {
        return null;
    }

}
