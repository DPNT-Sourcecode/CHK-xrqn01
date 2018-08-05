package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

interface PriceCalculationStrategy {

    PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products);

    @Getter
    @ToString
    @RequiredArgsConstructor
    class PriceCalculationResult {

        private final HashMultiset<Product> remainingProducts;
        private final int priceOfDiscountedProducts;

        static PriceCalculationResult nothingCalculated(HashMultiset<Product> remainingProducts) {
            return new PriceCalculationResult(remainingProducts, 0);
        }
    }

}
