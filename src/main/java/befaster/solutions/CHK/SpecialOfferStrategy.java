package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

interface SpecialOfferStrategy {

    OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products);

    @Getter
    @RequiredArgsConstructor
    static class OfferApplicationResult {

        private final HashMultiset<Product> productsAfterOfferHasBeenApplied;
        private final int moneySpentWithDiscount;

    }

}
