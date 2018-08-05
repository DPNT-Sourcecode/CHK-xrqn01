package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class MultipleProductsDiscountSpecialOfferStrategySpecTest extends Specification {

    static PRODUCT_ID = 'T' as char
    static INVALID_PRODUCT_ID = 'X' as char
    static REQUIRED_PRODUCTS_AMOUNT = 2 as int
    static PRODUCT_PRICE_WITHOUT_DISCOUNT = 10 as int
    static PRODUCTS_PRICE_WITH_DISCOUNT = (PRODUCT_PRICE_WITHOUT_DISCOUNT / 2) as int
    static INVALID_PRODUCT = new Product(INVALID_PRODUCT_ID, 1000)
    static PRODUCT = new Product(PRODUCT_ID, PRODUCT_PRICE_WITHOUT_DISCOUNT)

    @Subject
    def strategy = MultipleProductsDiscountSpecialOfferStrategy.builder().productId(PRODUCT.getId())
                                                               .productsAmount(REQUIRED_PRODUCTS_AMOUNT)
                                                               .discountPrice(PRODUCTS_PRICE_WITH_DISCOUNT)
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
          [PRODUCT] * REQUIRED_PRODUCTS_AMOUNT                  || []                                           | PRODUCTS_PRICE_WITH_DISCOUNT
          [INVALID_PRODUCT, PRODUCT] * REQUIRED_PRODUCTS_AMOUNT || [INVALID_PRODUCT] * REQUIRED_PRODUCTS_AMOUNT | PRODUCTS_PRICE_WITH_DISCOUNT
    }

}

