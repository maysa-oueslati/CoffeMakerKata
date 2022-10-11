package com.coffeemaker.coffeemaker.services.impl;

import com.coffeemaker.coffeemaker.enums.DrinkTypes;
import com.coffeemaker.coffeemaker.models.Order;
import com.coffeemaker.coffeemaker.repository.OrderRepository;
import com.coffeemaker.coffeemaker.services.BeverageQuantityChecker;
import com.coffeemaker.coffeemaker.services.DrinksOrderService;
import com.coffeemaker.coffeemaker.services.EmailNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coffeemaker.coffeemaker.models.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DrinksOrderServiceImpl implements DrinksOrderService {



    private OrderRepository orderRepository;

    private EmailNotifier emailNotifier;

    private BeverageQuantityChecker beverageQuantityChecker;


    public DrinksOrderServiceImpl(@Autowired OrderRepository orderRepository,
                                  @Autowired EmailNotifier emailNotifier,
                                  @Autowired BeverageQuantityChecker beverageQuantityChecker) {
        this.orderRepository = orderRepository;
        this.emailNotifier = emailNotifier;
        this.beverageQuantityChecker = beverageQuantityChecker;
    }

    @Override
    public Message orderDrink(String drinkType, Integer quantity, Integer numberOfSugar, Float givenMoney, boolean extraHot) {
        String template = "%s:%s:%s";
        Message message = new Message();
        try{

            boolean withStick = numberOfSugar > 0;
            DrinkTypes dType = DrinkTypes.valueOf(drinkType);



            if(dType.getPrice() <= givenMoney){
                String result = String.format(template,
                            dType.getCode().concat(extraHot ? "h":""),
                            numberOfSugar > 0 ? numberOfSugar : "",
                            withStick ? "0" : "");

                //save order in database
                Order order =  new Order();
                order.setOrderDate(new Date());
                order.setQuantity(quantity);
                order.setTotal(quantity * dType.getPrice());
                order.setType(dType);
                orderRepository.save(order);

                message.setSuccess(true);
                message.setResult(result);

            }else{
                message.setSuccess(false);
                message.setMessage("You cannot order a drink, please add :"+String.format("%.2f", dType.getPrice() - givenMoney)+" EUR and retry again.");
            }
            

        }catch(IllegalArgumentException e){
            log.error(e.getMessage(),e);
            message.setSuccess(true);
            message.setMessage("Internal server error: "+e.getMessage());

        }

        return message;
    }

    @Override
    public Message reportTotalDrinkSold(String drinkType) {
        List<Order> orders = new ArrayList<>();
        Message message = new Message();
        String template = "%s %s drinks sold and total amount is %s";
        try{
            DrinkTypes dType = DrinkTypes.valueOf(drinkType);
            if(dType != null){
                float total = 0;
                int numberOfDrinks = 0;
                orders = orderRepository.findByType(drinkType);
                for(Order order : orders){
                    total += order.getTotal();
                    numberOfDrinks += order.getQuantity();
                }

                String result = String.format(template,numberOfDrinks,dType.getDescription(),String.format("%.2f",total));
                message.setSuccess(true);
                message.setResult(result);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            message.setSuccess(false);
        }

        return message;
    }

    @Override
    public Message checkWaterMilkShortage(String drink) {
        //Check water/milk capacity
        Message message = new Message();
        String template = "%s not enough, sorry we cannot make a drink for the moment!";
        if(beverageQuantityChecker.isEmpty(drink)){
            emailNotifier.notifyMissingDrink("WATER");
            message.setSuccess(false);
            message.setMessage(String.format(template,drink));
        }

        return message;

    }
}
