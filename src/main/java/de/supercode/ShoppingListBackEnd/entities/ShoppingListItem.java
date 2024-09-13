package de.supercode.ShoppingListBackEnd.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class ShoppingListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    private String quantity;
    private String shop;
    private String note;
    private Boolean bought;

    public ShoppingListItem() {
        this.bought = false;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getShop() {
        return shop;
    }

    public String getNote() {
        return note;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItem that = (ShoppingListItem) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(shop, that.shop) && Objects.equals(note, that.note) && Objects.equals(bought, that.bought);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, shop, note, bought);
    }
}
