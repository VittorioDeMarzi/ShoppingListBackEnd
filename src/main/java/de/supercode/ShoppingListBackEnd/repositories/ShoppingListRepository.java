package de.supercode.ShoppingListBackEnd.repositories;

import de.supercode.ShoppingListBackEnd.entities.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingListItem, Long> {
    List<ShoppingListItem> findByBought(Boolean bought);
}
