package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Unroll

class FreebiePriceCalculationStrategySpecTest extends Specification {

    static PRODUCT_ID = 'T' as char
    static INVALID_PRODUCT_ID = 'X' as char
    static FREEBIE_PRODUCT_ID = 'F' as char
    static REQUIRED_PRODUCTS_AMOUNT = 2 as int
    static PRODUCT_PRICE = 10 as int
    static PRODUCT = new Product(PRODUCT_ID, PRODUCT_PRICE)
    static FREE_PRODUCT = new Product(FREEBIE_PRODUCT_ID, PRODUCT_PRICE)
    static INVALID_PRODUCT = new Product(INVALID_PRODUCT_ID, 1000)

    @Unroll
    "should return remaining products=#expectedRemainingProducts and price=#expectedPrice for given #givenProducts"() {
        given:
          def strategy = FreebiePriceCalculationStrategy.builder()
                                                        .productId(PRODUCT_ID)
                                                        .requiredProductsAmount(REQUIRED_PRODUCTS_AMOUNT)
                                                        .freeProductId(FREEBIE_PRODUCT_ID)
                                                        .build()

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

    def "should mark the same product category as freebie correctly"() {
        given: "strategy where product ID is the same as freebie product ID"
          def strategy = FreebiePriceCalculationStrategy.builder()
                                                        .productId(FREEBIE_PRODUCT_ID)
                                                        .requiredProductsAmount(3)
                                                        .freeProductId(FREEBIE_PRODUCT_ID)
                                                        .build()

          def givenProductsMultiSet = HashMultiset.create([FREE_PRODUCT, FREE_PRODUCT, FREE_PRODUCT])

        when:
          def result = strategy.applySpecialOfferTo givenProductsMultiSet

        then:
          result.getRemainingProducts() == HashMultiset.create([FREE_PRODUCT, FREE_PRODUCT])
          result.getPriceOfDiscountedProducts() == 0 as int
    }

}
