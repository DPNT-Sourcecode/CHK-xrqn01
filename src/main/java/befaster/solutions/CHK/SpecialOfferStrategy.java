package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.RequiredArgsConstructor;

interface SpecialOfferStrategy {

    HashMultiset<Product> applySpecialOfferTo(HashMultiset<Product> products);

    @RequiredArgsConstructor
    static class OfferApplicationResult {

        private final HashMultiset<Product> productsAfterOfferHasBeenApplied;
        private final int moneySpentWithDiscount;

    }

}
