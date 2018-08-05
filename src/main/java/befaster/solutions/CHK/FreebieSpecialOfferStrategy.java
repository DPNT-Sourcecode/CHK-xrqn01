package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
final class FreebieSpecialOfferStrategy implements SpecialOfferStrategy {

    private final char productId;
    private final int requiredProductsAmount;
    private final char freeProductId;

    @Override
    public OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products) {
        return null;
    }

}
