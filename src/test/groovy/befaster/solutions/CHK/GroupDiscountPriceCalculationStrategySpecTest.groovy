package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class GroupDiscountPriceCalculationStrategySpecTest extends Specification {

    static APPLICABLE_PRODUCTS = ['A' as char, 'B' as char, 'C' as char] as List<Character>
    static REQUIRED_PRODUCTS_AMOUNT = 2
    static DISCOUNTED_PRICE = 50

    static APPLICABLE_PRODUCT_A = new Product("A" as char, 10)
    static APPLICABLE_PRODUCT_B = new Product("B" as char, 20)
    static APPLICABLE_PRODUCT_C = new Product("C" as char, 30)
    static NON_APPLICABLE_PRODUCT = new Product("X" as char, 100)

    @Subject
    def strategy = new GroupDiscountPriceCalculationStrategy(APPLICABLE_PRODUCTS, REQUIRED_PRODUCTS_AMOUNT, DISCOUNTED_PRICE)

    @Unroll
    "should return remaining products=#expectedRemainingProducts and price=#expectedPrice for given #givenProducts"() {
        given:
          def givenProductsMultiSet = givenProducts != null ? HashMultiset.create(givenProducts as List<Product>) : null
          def expectedProductsMultiSet = HashMultiset.create expectedRemainingProducts

        when:
          def result = strategy.applySpecialOfferTo givenProductsMultiSet

        then:
          result.getRemainingProducts() == expectedProductsMultiSet
          result.getPriceOfDiscountedProducts() == expectedPrice as int

        where:
          givenProducts                                                        || expectedRemainingProducts                      | expectedPrice
          null                                                                 || []                                             | 0
          []                                                                   || []                                             | 0
          [NON_APPLICABLE_PRODUCT]                                             || [NON_APPLICABLE_PRODUCT]                       | 0
          [APPLICABLE_PRODUCT_A, NON_APPLICABLE_PRODUCT]                       || [APPLICABLE_PRODUCT_A, NON_APPLICABLE_PRODUCT] | 0
          [APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_A, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_B, APPLICABLE_PRODUCT_B, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_C, APPLICABLE_PRODUCT_C, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_B, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_C, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_B, APPLICABLE_PRODUCT_C, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_C, APPLICABLE_PRODUCT_A, NON_APPLICABLE_PRODUCT] || [NON_APPLICABLE_PRODUCT]                       | DISCOUNTED_PRICE
        and:
          [APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_C]   || [APPLICABLE_PRODUCT_A]                         | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_C, APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_A]   || [APPLICABLE_PRODUCT_A]                         | DISCOUNTED_PRICE
          [APPLICABLE_PRODUCT_C, APPLICABLE_PRODUCT_A, APPLICABLE_PRODUCT_B]   || [APPLICABLE_PRODUCT_A]                         | DISCOUNTED_PRICE
    }

}
