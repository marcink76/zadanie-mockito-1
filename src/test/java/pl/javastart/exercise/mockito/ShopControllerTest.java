package pl.javastart.exercise.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShopControllerTest {

    @Mock
    ShopRepository shopRepository;

    @Mock
    MusicPlayer musicPlayer;

    private ShopController shopController;
    private Shop shop;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        stock.put(new Item("Zioło", 14, 4, false), 5);

        shop = new Shop(0, stock, musicPlayer);

        when(shopRepository.findShop()).thenReturn(shop);

        shopController = new ShopController(shopRepository);
    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungling() {
        // given
        Human human = new Human();

        // when
        shopController.sellItem(human, "Piwo");
    }

    @Test(expected = OutOfStockException.class)
    public void shouldCheckIfItemAvailable() {
        // given
        Human human = new Human();

        // when
        shopController.sellItem(human, "Bułka");
    }

    @Test
    public void shoudNotSellIllegalStuffToPoliceman() {
        // given
        Human policeman = new Human();
        policeman.setName("Posterunkowy");
        policeman.setJob("Policjant");
        policeman.setAge(30);

        // when
        shopController.sellItem(policeman, "Zioło");

        // then
        verify(musicPlayer, times(0)).playSound(anyString());
    }

    @Test
    public void shoudSellItem() {
        // given
        Human kazik = new Human();
        kazik.setName("Kazik");
        kazik.setJob("Bezrobotny");
        kazik.setAge(30);
        kazik.setMoney(100);

        // when
        shopController.sellItem(kazik, "Piwo");

        // then
        verify(musicPlayer, times(1)).playSound("https://www.youtube.com/watch?v=Wj_OmtqVLxY");
        assertThat(kazik.getMoney(), is(96));
        assertThat(shop.getMoney(), is(4));

        Item piwo = shop.findItemByName("Piwo");
        Integer liczbaPiw = shop.getStock().get(piwo);
        assertThat(liczbaPiw, is(4));
    }


}
