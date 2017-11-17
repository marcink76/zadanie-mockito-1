package pl.javastart.exercise.mockito;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void shouldHaveItem() {
        // given
        Map<Item, Integer> stock = new HashMap<>();
        Item item = new Item("Piwo", 18, 3, true);
        stock.put(item, 1);

        Shop shop = new Shop(0,  stock, null);

        // when
        boolean hasItem = shop.hasItem("Piwo");

        // then
        assertThat(hasItem, is(true));
    }

    @Test
    public void shouldNotHaveItem() {
        // given
        Map<Item, Integer> stock = new HashMap<>();
        Item item = new Item("Sok", 18, 3, true);
        stock.put(item, 1);

        Shop shop = new Shop(0,  stock, null);

        // when
        boolean hasItem = shop.hasItem("Piwo");

        // then
        assertThat(hasItem, is(false));
    }

    @Test
    public void shouldNotHaveItem2() {
        // given
        Map<Item, Integer> stock = new HashMap<>();
        Item item = new Item("Piwo", 18, 3, true);
        stock.put(item, 0);

        Shop shop = new Shop(0,  stock, null);

        // when
        boolean hasItem = shop.hasItem("Piwo");

        // then
        assertThat(hasItem, is(false));
    }


}