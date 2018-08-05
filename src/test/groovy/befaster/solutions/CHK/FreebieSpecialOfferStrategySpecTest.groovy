package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class FreebieSpecialOfferStrategySpecTest extends Specification {

    static PRODUCT_ID = 'T' as char
    static INVALID_PRODUCT_ID = 'X' as char
    static FREEBIE_PRODUCT_ID = 'F' as char
    static REQUIRED_PRODUCTS_AMOUNT = 2 as int
    static PRODUCT_PRICE = 10 as int
    static PRODUCT = new Product(PRODUCT_ID, PRODUCT_PRICE)
    static FREE_PRODUCT = new Product(FREEBIE_PRODUCT_ID, PRODUCT_PRICE)
    static INVALID_PRODUCT = new Product(INVALID_PRODUCT_ID, 1000)

    @Subject
    def strategy = FreebieSpecialOfferStrategy.builder()
                                              .productId(PRODUCT_ID)
                                              .requiredProductsAmount(REQUIRED_PRODUCTS_AMOUNT)
                                              .freeProductId(FREEBIE_PRODUCT_ID)
                                              .build()

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
          givenProducts                                         || expectedRemainingProducts                    | expectedPrice
          null                                                  || []                                           | 0
          []                                                    || []                                           | 0
          [PRODUCT, INVALID_PRODUCT]                            || [PRODUCT, INVALID_PRODUCT]                   | 0
          [PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                  || []                                           | 0
          [INVALID_PRODUCT, PRODUCT] * REQUIRED_PRODUCTS_AMOUNT || [INVALID_PRODUCT] * REQUIRED_PRODUCTS_AMOUNT | PRODUCTS_PRICE_WITH_DISCOUNT
    }

}
