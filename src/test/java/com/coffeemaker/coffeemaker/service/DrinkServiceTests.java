package com.coffeemaker.coffeemaker.service;

import com.coffeemaker.coffeemaker.enums.DrinkTypes;
import com.coffeemaker.coffeemaker.models.Order;
import com.coffeemaker.coffeemaker.repository.OrderRepository;
import com.coffeemaker.coffeemaker.services.BeverageQuantityChecker;
import com.coffeemaker.coffeemaker.services.EmailNotifier;
import com.coffeemaker.coffeemaker.services.impl.DrinksOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.coffeemaker.coffeemaker.models.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@Slf4j
public class DrinkServiceTests {

        /*********** FIRST ITERATION TESTS ***************/



    @Mock
    private OrderRepository orderRepository;

    @Mock
    private EmailNotifier emailNotifier;

    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;

    @InjectMocks
    private DrinksOrderServiceImpl drinksOrderService;

    @Test
    void makeOneTeaWithOneSugar(){
        /*
         * make 1 tea with 1 sugar and a stick
         */

        Order order = new Order();
        order.setType(DrinkTypes.TEA);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.TEA.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderTeaMsg = drinksOrderService.orderDrink("TEA",1,1,1f,false);
        log.info("\n=>"+orderTeaMsg.getResult());
        Assertions.assertThat(orderTeaMsg.getResult()).isEqualTo("T:1:0");         
    }

    @Test
    void makeOneChocolateWithNoSugar(){

        /*
         * make 1 choclate with no sugar and therefore no stick
         */

        Order order = new Order();
        order.setType(DrinkTypes.CHOCOLATE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.CHOCOLATE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderChocolateMsg = drinksOrderService.orderDrink("CHOCOLATE",1,0,1f,false);
        log.info("\n=>"+orderChocolateMsg.getResult());
        Assertions.assertThat(orderChocolateMsg.getResult()).
                isEqualTo("H::");
    }

    @Test
    void makeOneCoffeeWithNoSugar(){

        /*
         * make 1 coffee with no sugar and therefore no stick
         */

        Order order = new Order();
        order.setType(DrinkTypes.COFFEE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.COFFEE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderCoffeeMsg = drinksOrderService.orderDrink("COFFEE",1,2,1f,false);
        log.info("\n=>"+orderCoffeeMsg.getResult());
        Assertions.assertThat(orderCoffeeMsg.getResult()).
                isEqualTo("C:2:0");
    }

    /*********** SECOND ITERATION TESTS ***************/

    @Test
    void makeSucceffulOrderWithEnoughMoney(){

        /*
         * cannot make a drink with less money
         */

        Order order = new Order();
        order.setType(DrinkTypes.COFFEE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.COFFEE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderSuccessCoffeeMsg = drinksOrderService.orderDrink("COFFEE",1,2,1f,false);
        log.info("\n=>"+orderSuccessCoffeeMsg.getResult());
        Assertions.assertThat(orderSuccessCoffeeMsg.isSuccess()).isTrue();
    }

    @Test
    void makeFailedOrderWithLessMoney(){

        /*
         * cannot make a drink with less money
         */

        Message orderFailedCoffeeMsg = drinksOrderService.orderDrink("COFFEE",1,2,0.2f,false);
        log.info("\n=>"+orderFailedCoffeeMsg.getMessage());
        Assertions.assertThat(orderFailedCoffeeMsg.isSuccess()).isFalse();
    }


    /*********** THIRD ITERATION TESTS ***************/

    @Test
    void makeOrangeOrderWithNoSugar(){

        /*
         *  make a orange drink with no sugare
         */

        Order order = new Order();
        order.setType(DrinkTypes.ORANGE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.ORANGE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderSuccessOrangeMsg = drinksOrderService.orderDrink("ORANGE",1,0,1f,false);
        log.info("\n=>"+orderSuccessOrangeMsg.getResult());
        Assertions.assertThat(orderSuccessOrangeMsg.isSuccess()).isTrue();
    }

    @Test
    void makeExtraHotCoffeeOrderWithNoSugar(){

        /*
         *  make a hot coffee
         */

        Order order = new Order();
        order.setType(DrinkTypes.COFFEE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.COFFEE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderHotCoffeeMsg = drinksOrderService.orderDrink("COFFEE",1,0,1f,true);
        log.info("\n=>"+orderHotCoffeeMsg.getResult());
        Assertions.assertThat(orderHotCoffeeMsg.isSuccess()).isTrue();
    }

    @Test
    void makeExtraHotChocolateOrderWithOneSugar(){

        /*
         *  make a hot chocolate drink with one sugar 
         */

        Order order = new Order();
        order.setType(DrinkTypes.CHOCOLATE);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.CHOCOLATE.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderHotChocolateMsg = drinksOrderService.orderDrink("CHOCOLATE",1,1,1f,true);
        log.info("\n=>"+orderHotChocolateMsg.getResult());
        Assertions.assertThat(orderHotChocolateMsg.isSuccess()).isTrue();
    }

    @Test
    void makeExtraHotTeaOrderWithTwoSugars(){

        /*
         *  make a hot tea drink with one sugar 
         */

        Order order = new Order();
        order.setType(DrinkTypes.TEA);
        order.setQuantity(1);
        order.setTotal(DrinkTypes.TEA.getPrice());
        order.setOrderDate(new Date());
        order.setId(1l);

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Message orderHotTeaMsg = drinksOrderService.orderDrink("TEA",1,2,1f,true);
        log.info("\n=>"+orderHotTeaMsg.getResult());
        Assertions.assertThat(orderHotTeaMsg.isSuccess()).isTrue();
    }

    /*********** FOURTH ITERATION TESTS ***************/

    @Test
    void reportTotalOrangeDrinksSold(){
        /*
         *  report Total Tea Drinks Sold
         */


        Order order4 = new Order();
        order4.setType(DrinkTypes.ORANGE);
        order4.setQuantity(1);
        order4.setTotal(DrinkTypes.ORANGE.getPrice());
        order4.setOrderDate(new Date());
        order4.setId(1l);

        Order order5 = new Order();
        order5.setType(DrinkTypes.ORANGE);
        order5.setQuantity(1);
        order5.setTotal(DrinkTypes.ORANGE.getPrice());
        order5.setOrderDate(new Date());
        order5.setId(1l);

        List<Order> listOrange = new ArrayList<>();
        listOrange.add(order4);
        listOrange.add(order5);


        Mockito.when(orderRepository.findByType(any(String.class))).thenReturn(listOrange);

        Message reportTeaMsg = drinksOrderService.reportTotalDrinkSold("ORANGE");
        log.info("\n=>"+reportTeaMsg.getResult());
        Assertions.assertThat(reportTeaMsg.isSuccess()).isTrue();
    }

    @Test
    void reportTotalChocolateDrinksSold(){
        /*
         *  report Total Chocolate Drinks Sold
         */

        Order order1 = new Order();
        order1.setType(DrinkTypes.CHOCOLATE);
        order1.setQuantity(1);
        order1.setTotal(DrinkTypes.CHOCOLATE.getPrice());
        order1.setOrderDate(new Date());
        order1.setId(1l);

        Order order2 = new Order();
        order2.setType(DrinkTypes.CHOCOLATE);
        order2.setQuantity(1);
        order2.setTotal(DrinkTypes.CHOCOLATE.getPrice());
        order2.setOrderDate(new Date());
        order2.setId(1l);

        Order order3 = new Order();
        order3.setType(DrinkTypes.CHOCOLATE);
        order3.setQuantity(1);
        order3.setTotal(DrinkTypes.CHOCOLATE.getPrice());
        order3.setOrderDate(new Date());
        order3.setId(1l);

        List<Order> listChoc = new ArrayList<>();
        listChoc.add(order1);
        listChoc.add(order2);
        listChoc.add(order3);

        Mockito.when(orderRepository.findByType(any(String.class))).thenReturn(listChoc);

        Message reportChocolateMsg = drinksOrderService.reportTotalDrinkSold("CHOCOLATE");
        log.info("\n=>"+reportChocolateMsg.getResult());
        Assertions.assertThat(reportChocolateMsg.isSuccess()).isTrue();
    }



    /*********** FIFTH ITERATION TESTS ***************/

    @Test
    void waterShortageTest(){

        /*
         *  check water capacity
         */

        Mockito.when(beverageQuantityChecker.isEmpty(any(String.class))).thenReturn(true);

        Message checkWaterMsg = drinksOrderService.checkWaterMilkShortage("Water");
        log.info("\n=>  "+checkWaterMsg.getMessage());
        Assertions.assertThat(checkWaterMsg.isSuccess()).isFalse();
    }

    @Test
    void milkShortageTest(){

        /*
         *  check water capacity
         */

        Mockito.when(beverageQuantityChecker.isEmpty(any(String.class))).thenReturn(true);

        Message checkMilkMsg = drinksOrderService.checkWaterMilkShortage("Milk");
        log.info("\n=>  "+checkMilkMsg.getMessage());
        Assertions.assertThat(checkMilkMsg.isSuccess()).isFalse();
    }


}
