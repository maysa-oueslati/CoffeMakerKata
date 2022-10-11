package com.coffeemaker.coffeemaker.services.impl;

import com.coffeemaker.coffeemaker.services.BeverageQuantityChecker;
import org.springframework.stereotype.Service;

@Service
public class BeverageQuantityCheckerImpl implements BeverageQuantityChecker {
    @Override
    public boolean isEmpty(String drink) {
        return false;
    }
}
