package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

interface SpecialOfferStrategy {

    OfferApplicationResult applySpecialOfferTo(HashMultiset<Product> products);

    @Getter
    @RequiredArgsConstructor
    class OfferApplicationResult {

        private final HashMultiset<Product> remainingProducts;
        private final int priceOfDiscountedProducts;

        static OfferApplicationResult nothingDiscounted(HashMultiset<Product> remainingProducts) {
            return new OfferApplicationResult(remainingProducts, 0);
        }

    }

}
