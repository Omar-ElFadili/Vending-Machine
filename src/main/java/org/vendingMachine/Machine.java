package org.vendingMachine;

import org.vendingMachine.Exceptions.CoinsNotSufficientException;
import org.vendingMachine.Exceptions.ProductNotExistException;

import java.util.HashMap;
import java.util.Map;

public class Machine {
    private HashMap<Product, Integer> products;
    private HashMap<Coins, Integer> coinsReserve;

    public Machine(HashMap<Product, Integer> products, HashMap<Coins, Integer> coinsReserve) {
        this.products = products;
        this.coinsReserve = coinsReserve;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public HashMap<Coins, Integer> getCoinsReserve() {
        return coinsReserve;
    }

    public void setCoinsReserve(HashMap<Coins, Integer> coinsReserve) {
        this.coinsReserve = coinsReserve;
    }
    //add product to the machine
    public HashMap<Product, Integer> addProducts(HashMap<Product, Integer> newProducts){
        this.products.put(Product.WATER, products.get(Product.WATER) + newProducts.get(Product.WATER));
        this.products.put(Product.COCA, products.get(Product.COCA) + newProducts.get(Product.COCA));
        this.products.put(Product.TWIX, products.get(Product.TWIX) + newProducts.get(Product.TWIX));
        this.products.put(Product.BUENO, products.get(Product.BUENO) + newProducts.get(Product.BUENO));
        return this.products;
    }
    //this function add user coins to the machine coins
    public void addMachineCoins(HashMap<Coins, Integer> userCoins){
        coinsReserve.put(Coins.ONE, coinsReserve.get(Coins.ONE) + userCoins.get(Coins.ONE));
        coinsReserve.put(Coins.TWO, coinsReserve.get(Coins.TWO) + userCoins.get(Coins.TWO));
        coinsReserve.put(Coins.FIVE, coinsReserve.get(Coins.FIVE) + userCoins.get(Coins.FIVE));
        coinsReserve.put(Coins.TEN, coinsReserve.get(Coins.TEN) + userCoins.get(Coins.TEN));
    }
    public Product selectProduct(Product userProduct, HashMap<Coins, Integer> userCoins) throws Exception {
        if(verifyProduct(userProduct)){
            addMachineCoins(userCoins);
            int userTotalCoins = getUserTotalCoins(userCoins);
            if(userTotalCoins >= getProductPrice(userProduct)){
                int resteCoins = userTotalCoins - getProductPrice(userProduct);
                remainingChange(resteCoins);
                products.put(userProduct, products.get(userProduct) - 1);
                return userProduct;
            }
            else{
                throw new CoinsNotSufficientException();
            }
        }
        throw new ProductNotExistException();
    }
    //this function verify if the product exist in the machine
    public boolean verifyProduct(Product product){
        if(products.get(product) > 0){
            return true;
        }
        else return false;
    }
    //this function return the sum that user entered
    public int getUserTotalCoins(HashMap<Coins, Integer> userCoins){
        return userCoins.get(Coins.ONE) + userCoins.get(Coins.TWO) * 2 + userCoins.get(Coins.FIVE)*5 + userCoins.get(Coins.TEN)*10;
    }
    //this function return the price of product wanted
    public int getProductPrice(Product product){
        HashMap<Product, Integer> productsPrice = new HashMap<>();
        productsPrice.put(Product.WATER, 5);
        productsPrice.put(Product.COCA, 7);
        productsPrice.put(Product.TWIX, 10);
        productsPrice.put(Product.BUENO, 12);
        return productsPrice.get(product);
    }
    //this function remaining change to user
    public void remainingChange(int resteCoins){
        int returnedCoins = resteCoins;
        for(Map.Entry<Coins, Integer> entry : coinsReserve.entrySet()){
            int numberCoins = resteCoins / entry.getKey().getValue();
            int tackingCoins = Math.min(entry.getValue(), numberCoins);
            this.coinsReserve.put(entry.getKey(), entry.getValue() - tackingCoins);
            returnedCoins -= tackingCoins * entry.getKey().getValue();
            if (resteCoins == 0){
                break;
            }
        }
    }
}

