import org.junit.jupiter.api.Test;
import org.vendingMachine.Coins;
import org.vendingMachine.Exceptions.CoinsNotSufficientException;
import org.vendingMachine.Exceptions.ProductNotExistException;
import org.vendingMachine.Machine;
import org.vendingMachine.Product;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestMachine {
    @Test
    public void shouldAddProductsToMachine(){
        HashMap<Product, Integer> products  = new HashMap<>();
        products.put(Product.WATER, 0);
        products.put(Product.COCA, 0);
        products.put(Product.TWIX,0);
        products.put(Product.BUENO, 0);
        HashMap<Coins, Integer> coinsReserve = new HashMap<>();

        Machine machine = new Machine(products, coinsReserve);
        HashMap<Product, Integer> newProducts  = new HashMap<>();
        newProducts.put(Product.WATER, 10);
        newProducts.put(Product.COCA, 15);
        newProducts.put(Product.TWIX, 20);
        newProducts.put(Product.BUENO, 10);
        assertEquals(newProducts, machine.addProducts(newProducts));
    }

    @Test
    public void shouldSelectWaterProduct() throws Exception {
        HashMap<Product, Integer> products  = new HashMap<>();
        products.put(Product.WATER, 10);
        products.put(Product.COCA, 10);
        products.put(Product.TWIX,10);
        products.put(Product.BUENO, 10);

        HashMap<Coins, Integer> coinsReserve = new HashMap<>();
        coinsReserve.put(Coins.ONE, 20);
        coinsReserve.put(Coins.TWO, 20);
        coinsReserve.put(Coins.FIVE, 20);
        coinsReserve.put(Coins.TEN, 20);
        Machine machine = new Machine(products, coinsReserve);
        assertEquals(Product.WATER, machine.selectProduct(Product.WATER, coinsReserve));
    }

    @Test
    public void shouldGenerateExceptionWhenProductNotFounded() throws Exception {
        HashMap<Product, Integer> products  = new HashMap<>();
        products.put(Product.WATER, 0);
        products.put(Product.COCA, 10);
        products.put(Product.TWIX,10);
        products.put(Product.BUENO, 10);
        HashMap<Coins, Integer> coinsReserve = new HashMap<>();
        coinsReserve.put(Coins.ONE, 20);
        coinsReserve.put(Coins.TWO, 20);
        coinsReserve.put(Coins.FIVE, 20);
        coinsReserve.put(Coins.TEN, 20);
        Machine machine = new Machine(products, coinsReserve);
        assertThrows(ProductNotExistException.class, () -> machine.selectProduct(Product.WATER, coinsReserve));
    }
    @Test
    public void shouldGenerateExceptionWhenCoinsNotExist(){
        HashMap<Product, Integer> products  = new HashMap<>();
        products.put(Product.WATER, 10);
        products.put(Product.COCA, 10);
        products.put(Product.TWIX,10);
        products.put(Product.BUENO, 10);
        HashMap<Coins, Integer> coinsReserve = new HashMap<>();
        coinsReserve.put(Coins.ONE, 0);
        coinsReserve.put(Coins.TWO, 0);
        coinsReserve.put(Coins.FIVE, 0);
        coinsReserve.put(Coins.TEN, 0);
        Machine machine = new Machine(products, coinsReserve);

        assertThrows(CoinsNotSufficientException.class, () -> machine.selectProduct(Product.WATER, coinsReserve));
    }


}
