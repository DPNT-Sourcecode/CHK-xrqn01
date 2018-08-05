package befaster.solutions.CHK;

import com.google.common.collect.HashMultiset;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static befaster.solutions.CHK.PriceCalculationStrategy.PriceCalculationResult.nothingCalculated;
import static lombok.AccessLevel.PACKAGE;

@NoArgsConstructor(access = PACKAGE)
class NonDiscountStrategy implements PriceCalculationStrategy {

    @Override
    public PriceCalculationResult applySpecialOfferTo(HashMultiset<Product> products) {
        return Optional.ofNullable(products)
                       .flatMap(p -> p.entrySet()
                                      .stream()
                                      .map(entry -> entry.getCount() * entry.getElement()
                                                                            .getPrice())
                                      .reduce(Integer::sum))
                       .map(price -> new PriceCalculationResult(HashMultiset.create(), price))
                       .orElse(nothingCalculated(HashMultiset.create()));
    }

}
