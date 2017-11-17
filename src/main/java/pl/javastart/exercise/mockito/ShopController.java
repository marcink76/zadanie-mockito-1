package pl.javastart.exercise.mockito;

import java.util.Map;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();

    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
            }
            if(!item.isLegal() && human.getJob().equals("Policjant")) {
                // nic
            }

            shop.setMoney(item.getPrice());
            human.setMoney(human.getMoney() - item.getPrice());

            Map<Item, Integer> stock = shop.getStock();
            stock.put(item, 4);

            shop.playCashSound();

        } else {
            // TODO sklep nie ma danego przedmiotu, wyrzuć wyjątek OutOfStockException
            throw new OutOfStockException();
        }

    }

}
