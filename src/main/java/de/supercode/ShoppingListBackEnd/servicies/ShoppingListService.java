package de.supercode.ShoppingListBackEnd.servicies;

import de.supercode.ShoppingListBackEnd.entities.ShoppingListItem;
import de.supercode.ShoppingListBackEnd.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {
    ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public void addProductToShoppingList(ShoppingListItem shoppingListItem) {
        shoppingListRepository.save(shoppingListItem);
    }

    public List<ShoppingListItem> getShoppingList() {
        return shoppingListRepository.findAll();
    }

    public List<ShoppingListItem> findProductByStatus(boolean bought) {
        return shoppingListRepository.findByBought(bought);
    }

    public void updateProduct(long productId) {
        ShoppingListItem item = shoppingListRepository.findById(productId).orElseThrow();
        item.setBought(!item.getBought());
        shoppingListRepository.save(item);

    }

    public void deleteProduct(long listItemId) {
        shoppingListRepository.deleteById(listItemId);
    }
}
