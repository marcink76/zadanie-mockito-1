package pl.javastart.exercise.mockito;

import java.util.Map;
import java.util.Set;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;
    private MusicPlayer musicPlayer;

    public Shop(int money, Map<Item, Integer> stock, MusicPlayer musicPlayer) {
        this.money = money;
        this.stock = stock;
        this.musicPlayer = musicPlayer;
    }

    void playCashSound() {

        musicPlayer.playSound("https://www.youtube.com/watch?v=Wj_OmtqVLxY");

        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) {

        Set<Map.Entry<Item, Integer>> entries = stock.entrySet();

        for (Map.Entry<Item, Integer> entry : entries) {

            Item item = entry.getKey();
            Integer count = entry.getValue();

            if(item.getName() != null && item.getName().equals(itemName)) {
                if(count > 0) {
                    return true;
                }
            }
        }

        // OPCJA 2:

        Set<Item> items = stock.keySet();
        for (Item item : items) {
            if(item.getName().equals(itemName)) {
                Integer count = stock.get(item);
                if(count > 0) {
                    return true;
                }
            }
        }


        return false;
    }

    public Item findItemByName(String itemName) {

        for (Item item : stock.keySet()) {
            if(item.getName().equals(itemName)) {
                return item;
            }
        }

        return null;
    }

    public int getMoney() {
        return money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setStock(Map<Item, Integer> stock) {
        this.stock = stock;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }
}
