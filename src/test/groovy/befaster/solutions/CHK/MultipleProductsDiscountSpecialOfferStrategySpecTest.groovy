package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class MultipleProductsDiscountSpecialOfferStrategySpecTest extends Specification {

    static TEST_VALID_PRODUCT_ID = 'T' as char
    static TEST_INVALID_PRODUCT_ID = 'X' as char
    static REQIRED_PRODUCTS_AMOUNT = 2 as int
    static PRODUCT_PRICE_WITHOUT_DISCOUNT = 10 as int
    static PRODUCTS_PRICE_WITH_DISCOUNT = (PRODUCTS_PRICE_WITH_DISCOUNT / 2) as int
    static TEST_INVALID_PRODUCT = new Product(TEST_VALID_PRODUCT_ID, PRODUCT_PRICE_WITHOUT_DISCOUNT)
    static TEST_PRODUCT = new Product(TEST_VALID_PRODUCT_ID, PRODUCT_PRICE_WITHOUT_DISCOUNT)

    @Subject
    def strategy = new MultipleProductsDiscountSpecialOfferStrategy(TEST_PRODUCT, REQIRED_PRODUCTS_AMOUNT, PRODUCTS_PRICE_WITH_DISCOUNT)

    @Unroll
    "should return remaining products=#expectedRemainingProducts and price=#expectedPrice for given #givenProducts"() {
        when:
          strategy.
    }

}

