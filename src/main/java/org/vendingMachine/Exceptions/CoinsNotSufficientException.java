package org.vendingMachine.Exceptions;

import org.vendingMachine.Coins;

public class CoinsNotSufficientException extends Exception{
    public CoinsNotSufficientException(){
        super("your coins not sufficient for buying this product");
    }
}
