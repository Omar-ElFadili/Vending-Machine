package org.vendingMachine.Exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException() {
        super("this product has been expired");
    }
}
