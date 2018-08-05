package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

interface PriceCalculationStrategy {

    PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products);

    @Getter
    @RequiredArgsConstructor
    class PriceCalculationResult {

        private final HashMultiset<Product> remainingProducts;
        private final int priceOfDiscountedProducts;

        static PriceCalculationResult nothingDiscounted(HashMultiset<Product> remainingProducts) {
            return new PriceCalculationResult(remainingProducts, 0);
        }

    }

}
