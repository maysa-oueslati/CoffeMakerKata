package com.coffeemaker.coffeemaker.services.impl;

import com.coffeemaker.coffeemaker.services.EmailNotifier;
import org.springframework.stereotype.Service;

@Service
public class EmailNotifierImpl implements EmailNotifier {
    @Override
    public void notifyMissingDrink(String drink) {

    }
}
