package com.coffeemaker.coffeemaker.services;

import com.coffeemaker.coffeemaker.enums.DrinkTypes;
import com.coffeemaker.coffeemaker.models.Message;


public interface DrinksOrderService {

    Message orderDrink(String drinkType, Integer quantity, Integer numberOfSugar, Float givenMoney, boolean extraHot);
    Message reportTotalDrinkSold(String drinkType);

    Message checkWaterMilkShortage(String drink);

}
