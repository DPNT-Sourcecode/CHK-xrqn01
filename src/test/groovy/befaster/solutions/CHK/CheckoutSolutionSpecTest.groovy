package befaster.solutions.CHK

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class CheckoutSolutionSpecTest extends Specification {

    static INVALID_PRODUCT_ID = '!'

    @Subject
    def solution = new CheckoutSolution()

    @Unroll
    "should return #expectedPrice for given products #productsString"() {
        when:
          def actualPrice = solution.checkout productsString

        then:
          actualPrice == expectedPrice

        where:
          productsString                                               || expectedPrice
          null                                                         || -1
          "${INVALID_PRODUCT_ID}"                                      || -1
          ""                                                           || 0
          "A"                                                          || 50
          "B"                                                          || 30
          "C"                                                          || 20
          "D"                                                          || 15
          "E"                                                          || 40
          "F"                                                          || 10
          "G"                                                          || 20
          "H"                                                          || 10
          "I"                                                          || 35
          "J"                                                          || 60
          "K"                                                          || 80
          "L"                                                          || 90
          "M"                                                          || 15
          "N"                                                          || 40
          "O"                                                          || 10
          "P"                                                          || 50
          "Q"                                                          || 30
          "R"                                                          || 50
          "S"                                                          || 30
          "T"                                                          || 20
          "U"                                                          || 40
          "V"                                                          || 50
          "W"                                                          || 20
          "X"                                                          || 90
          "Y"                                                          || 10
          "Z"                                                          || 50
          "a"                                                          || -1
          "-"                                                          || -1
          "ABCa"                                                       || -1
          "AxA"                                                        || -1
          "ABCDEFGHIJKLMNOPQRSTUVWXYZ"                                 || 965
          "A"                                                          || 50
          "AA"                                                         || 100
          "AAA"                                                        || 130
          "AAAA"                                                       || 180
          "AAAAA"                                                      || 200
          "AAAAAA"                                                     || 250
          "AAAAAAA"                                                    || 300
          "AAAAAAAA"                                                   || 330
          "AAAAAAAAA"                                                  || 380
          "AAAAAAAAAA"                                                 || 400
          "P"                                                          || 50
          "PP"                                                         || 100
          "PPP"                                                        || 150
          "PPPP"                                                       || 200
          "PPPPP"                                                      || 200
          "PPPPPP"                                                     || 250
          "PPPPPPP"                                                    || 300
          "PPPPPPPP"                                                   || 350
          "PPPPPPPPP"                                                  || 400
          "PPPPPPPPPP"                                                 || 400
          "UUU"                                                        || 120
          "UUUU"                                                       || 120
          "UUUUU"                                                      || 160
          "UUUUUUUU"                                                   || 240
          "UUUUUUUU"                                                   || 240
          "EE"                                                         || 80
          "EEB"                                                        || 80
          "EEEB"                                                       || 120
          "EEEEBB"                                                     || 160
          "BEBEEE"                                                     || 160
          "RRR"                                                        || 150
          "RRRQ"                                                       || 150
          "RRRRQ"                                                      || 200
          "RRRRRRQQ"                                                   || 300
          "RRRQRQRR"                                                   || 300
          "A"                                                          || 50
          "AA"                                                         || 100
          "AAA"                                                        || 130
          "AAAA"                                                       || 180
          "AAAAA"                                                      || 200
          "AAAAAA"                                                     || 250
          "H"                                                          || 10
          "HH"                                                         || 20
          "HHH"                                                        || 30
          "HHHH"                                                       || 40
          "HHHHH"                                                      || 45
          "HHHHHH"                                                     || 55
          "HHHHHHH"                                                    || 65
          "HHHHHHHH"                                                   || 75
          "HHHHHHHHH"                                                  || 85
          "HHHHHHHHHH"                                                 || 80
          "HHHHHHHHHHH"                                                || 90
          "HHHHHHHHHHHH"                                               || 100
          "HHHHHHHHHHHHH"                                              || 110
          "HHHHHHHHHHHHHH"                                             || 120
          "HHHHHHHHHHHHHHH"                                            || 125
          "HHHHHHHHHHHHHHHH"                                           || 135
          "HHHHHHHHHHHHHHHHH"                                          || 145
          "HHHHHHHHHHHHHHHHHH"                                         || 155
          "HHHHHHHHHHHHHHHHHHH"                                        || 165
          "HHHHHHHHHHHHHHHHHHHH"                                       || 160
          "V"                                                          || 50
          "VV"                                                         || 90
          "VVV"                                                        || 130
          "VVVV"                                                       || 180
          "VVVVV"                                                      || 220
          "VVVVVV"                                                     || 260
          "B"                                                          || 30
          "BB"                                                         || 45
          "BBB"                                                        || 75
          "BBBB"                                                       || 90
          "NNN"                                                        || 120
          "NNNM"                                                       || 120
          "NNNNM"                                                      || 160
          "NNNNNNMM"                                                   || 240
          "NNNMNMNN"                                                   || 240
          "FF"                                                         || 20
          "FFF"                                                        || 20
          "FFFF"                                                       || 30
          "FFFFFF"                                                     || 40
          "FFFFFF"                                                     || 40
          "K"                                                          || 80
          "KK"                                                         || 150
          "KKK"                                                        || 230
          "KKKK"                                                       || 300
          "Q"                                                          || 30
          "QQ"                                                         || 60
          "QQQ"                                                        || 80
          "QQQQ"                                                       || 110
          "QQQQQ"                                                      || 140
          "QQQQQQ"                                                     || 160
          "V"                                                          || 50
          "VV"                                                         || 90
          "VVV"                                                        || 130
          "VVVV"                                                       || 180
          "H"                                                          || 10
          "HH"                                                         || 20
          "HHH"                                                        || 30
          "HHHH"                                                       || 40
          "HHHHH"                                                      || 45
          "HHHHHH"                                                     || 55
          "HHHHHHH"                                                    || 65
          "HHHHHHHH"                                                   || 75
          "HHHHHHHHH"                                                  || 85
          "HHHHHHHHHH"                                                 || 80
          "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"       || 1880
          "LGCKAQXFOSKZGIWHNRNDITVBUUEOZXPYAVFDEPTBMQLYJRSMJCWH"       || 1880
          "AAAAAPPPPPUUUUEEBRRRQAAAHHHHHHHHHHVVVBBNNNMFFFKKQQQVVHHHHH" || 1640
          "PPPPQRUVPQRUVPQRUVSU"                                       || 740
    }


}