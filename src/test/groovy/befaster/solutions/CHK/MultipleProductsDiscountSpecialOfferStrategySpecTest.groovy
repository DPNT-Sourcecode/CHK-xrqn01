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
    static PRODUCTS_PRICE_WITH_DISCOUNT = (PRODUCTS_PRICE_WITH_DISCOUNT / 2) as int
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
          HashMultiset.create()

        when:
          strategy.applySpecialOfferTo()

        where:
          givenProducts              || expectedRemainingProducts  | expectedPrice
          []                         || []                         | 0
          [PRODUCT, INVALID_PRODUCT] || [PRODUCT, INVALID_PRODUCT] | 0
          [PRODUCT, PRODUCT]         || []                         | PRODUCTS_PRICE_WITH_DISCOUNT
    }

}

