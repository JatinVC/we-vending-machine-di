package com.mthree.DTO;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String itemName;
    private BigDecimal price;


    public Item() {
    }

    public Item(String itemName, BigDecimal price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Item itemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    public Item price(BigDecimal price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, price);
    }

    @Override
    public String toString() {
        return "{" +
            " itemName='" + getItemName() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

}
