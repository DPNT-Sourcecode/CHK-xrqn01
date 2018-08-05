package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject

class MultipleProductsDiscountSpecialOfferStrategySpecTest extends Specification {

    static TEST_VALID_PRODUCT_ID = 'T' as char
    static TEST_INVALID_PRODUCT_ID = 'X' as char
    static REQIRED_PRODUCTS_AMOUNT = 2
    static PRODUCT_PRICE_WITHOUT_DISCOUNT = 10
    static TEST_PRODUCT = new Product(TEST_VALID_PRODUCT_ID, PRODUCT_PRICE_WITHOUT_DISCOUNT)

    @Subject
    def strategy = new MultipleProductsDiscountSpecialOfferStrategy(TEST_PRODUCT,)

}

