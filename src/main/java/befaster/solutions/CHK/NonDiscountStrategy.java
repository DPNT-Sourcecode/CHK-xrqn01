package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;

@NoArgsConstructor(access = PACKAGE)
class NonDiscountStrategy implements PriceCalculationStrategy {

    @Override
    public PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products) {
        return Optional.ofNullable(products)
                .map(p -> p.entrySet().ma)
    }

}
