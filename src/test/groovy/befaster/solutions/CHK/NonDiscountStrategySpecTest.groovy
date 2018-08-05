package befaster.solutions.CHK

import com.google.common.collect.HashMultiset
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class NonDiscountStrategySpecTest extends Specification {

    @Subject
    def strategy = new NonDiscountStrategy()

    @Unroll
    "should return no remaining products and a sum of their price=#expectedPrice for given #givenProducts"() {
        given:
          def givenProductsMultiSet = givenProducts != null ? HashMultiset.create(givenProducts) : null

        when:
          def result = strategy.applySpecialOfferTo givenProductsMultiSet

        then:
          result.getRemainingProducts() == HashMultiset.create()
          result.getPriceOfDiscountedProducts() == expectedPrice

        where:
          _ | givenProducts
          _ | null
          _ | []
          _ | randomProducts(1)
          _ | randomProducts(5)
          _ | randomProducts(10)

          expectedPrice = givenProducts?.inject(0, { a, b -> a.price + b.price }) ?: 0
    }

    static randomProducts(int amount) {
        return (0..amount).collect { randomProduct() }
    }

    static randomProduct() {
        return new Product(randomProductId(), randomPrice())
    }

    static randomProductId() {
        (new Random().nextInt(25) + 65) as char
    }

    static randomPrice() {
        new Random().nextInt(100)
    }

}
