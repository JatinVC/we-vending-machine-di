package com.mthree.DTO;

import java.util.Objects;

public class VendingMachineItem {
    private Item item;
    private int quantity;


    public VendingMachineItem() {
    }

    public VendingMachineItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public VendingMachineItem item(Item item) {
        setItem(item);
        return this;
    }

    public VendingMachineItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof VendingMachineItem)) {
            return false;
        }
        VendingMachineItem vendingMachineItem = (VendingMachineItem) o;
        return Objects.equals(item, vendingMachineItem.item) && quantity == vendingMachineItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " item='" + getItem() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}
