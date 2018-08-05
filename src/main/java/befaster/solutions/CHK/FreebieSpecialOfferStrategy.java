package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Builder
@RequiredArgsConstructor(access = PRIVATE)
final class FreebieSpecialOfferStrategy implements SpecialOfferStrategy {

    private final char productId;
    private final int requiredProductsAmount;
    private final char freeProductId;

    @Override
    public OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products) {
        final HashMultiset<Product> productsOrEmptyIfNull = Optional.ofNullable(products)
                                                                    .orElse(HashMultiset.create());
        productsOrEmptyIfNull.entrySet()
                             .stream()
                             .filter(productEntry -> productEntry.getElement()
                                                                 .getId() == productId)
                             .map(productEntry -> {
                                
                             })
    }

}
