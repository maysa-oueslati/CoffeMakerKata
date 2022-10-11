
Coffee Maker Kata  
This project consist of 5 iterations, in each iteration there's an implementation of  test cases and logic to fulfill the requests:

Note all test cases are implemented in this test class: 
https://github.com/maysa-oueslati/CoffeMakerKata/blob/main/src/test/java/com/coffeemaker/coffeemaker/service/DrinkServiceTests.java

The logic behind the unit tests generate command/messages to be passed to the coffee maker.

First iteration: Making drinks
The following unit tests represent scenarios of ordring a drink: makeOneTeaWithOneSugar() => Make one tea with one sugar and a stick makeOneChocolateWithNoSugar() => Make one chocolate with no sugar and therefore no stick makeOneCoffeeWithNoSugar() => Make one coffee with no sugar and therefore no stick

Second iteration: Going into business
This iteration adds control on the amount of moeny inserted, so making drinks is only possible if enough money inserted. makeSucceffulOrderWithEnoughMoney() => Present a succefful order makeFailedOrderWithLessMoney() => Present a failed order

Thrid iteration: Extra hot
This iteration added option (Hot drink) to the choices as well a new drink type (Orange) makeOrangeOrderWithNoSugar() => Make an orange drink with no sugar makeExtraHotCoffeeOrderWithNoSugar() => Make extra hot coffee with no sugar makeExtraHotChocolateOrderWithOneSugar() => Make extra hot chocolate with one sugar makeExtraHotTeaOrderWithTwoSugars() => Make extra hot tea with 2 sugars

Fourth iteration: Making money
This iteration provide some reporting on sales. reportTotalOrangeDrinksSold() => Report total orange drinks sold and total amount of incomes. reportTotalChocolateDrinksSold() => Report total chocolate drinks sold and total amount of incomes.

Fifth iteration: Making money
This iteration handle shortage of drink and customer/company notification waterShortageTest() => Check if enough water exists and notify customer/comapny via message on console / email if not enough milkShortageTest() => Check if enough milk exists and notify customer/comapny via message on console / email if not enough
