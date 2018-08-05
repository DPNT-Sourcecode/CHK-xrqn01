package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class NonDiscountStrategySpecTest extends Specification {

    @Subject
    def strategy = new NonDiscountStrategy()

    @Unroll
    "should return remaining products=#expectedRemainingProducts and price=#expectedPrice for given #givenProducts"() {
        given:
          def givenProductsMultiSet = givenProducts != null ? HashMultiset.create(givenProducts) : null
          def expectedProductsMultiSet = HashMultiset.create expectedRemainingProducts

        when:
          def result = strategy.applySpecialOfferTo givenProductsMultiSet

        then:
          result.getRemainingProducts() == expectedProductsMultiSet
          result.getPriceOfDiscountedProducts() == expectedPrice

        where:
          givenProducts                                                            || expectedRemainingProducts                                  | expectedPrice
          null                                                                     || []                                                         | 0
          []                                                                       || []                                                         | 0
          [PRODUCT, INVALID_PRODUCT]                                               || [PRODUCT, INVALID_PRODUCT]                                 | 0
          [PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                                     || [PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                       | 0
          [INVALID_PRODUCT, PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                    || [INVALID_PRODUCT, PRODUCT] * REQUIRED_PRODUCTS_AMOUNT      | 0
          [FREE_PRODUCT] + ([PRODUCT] * REQUIRED_PRODUCTS_AMOUNT)                  || [PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                       | 0
          [FREE_PRODUCT, INVALID_PRODUCT] + ([PRODUCT] * REQUIRED_PRODUCTS_AMOUNT) || [INVALID_PRODUCT] + ([PRODUCT] * REQUIRED_PRODUCTS_AMOUNT) | 0
    }

    def prices() {
        
    }

}
