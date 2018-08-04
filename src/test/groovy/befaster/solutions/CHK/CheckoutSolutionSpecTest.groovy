package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CheckoutSolutionSpecTest extends Specification {

    @Subject
    def solution = new CheckoutSolution()

    @Unroll
    "should return #expectedPrice for given #productsString"() {


        where:
          productsString || expectedPrice
          null           || -1
        
    }

}
