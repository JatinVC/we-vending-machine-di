package com.mthree.DAO;

import java.util.List;

import com.mthree.DTO.VendingMachineItem;

public interface Inventory {

    /**
     * Get all the items present on the system.
     * @return a list of all items in the system.
     */
    List<VendingMachineItem> getItems();

    /**
     * get item with item name 
     * @param itemName unique item name
     * @return return the item corresponding to the item name.
     */
    VendingMachineItem getItem(String itemName);

    /**
     * add new item to the vending machine
     * @param itemName the name of the item to be added to the system.
     * @param newItem the item that was added to the system.
     * @return the vending machine item that was made.
     */
    VendingMachineItem addNewItem(String itemName, VendingMachineItem newItem);

    /**
     * Edit the given item and place it back into the list
     * and return the edited item object
     * @param itemName the name of the item
     * @param editedItem the item object that was edited
     * @return the item object that was edited.
     */
    VendingMachineItem editItem(String itemName, VendingMachineItem editedItem);

    /**
     * Purchase the item by decrementing its quantity by one.
     * @param itemName the item that was bought.
     * @return the new item object with the updated quantity.
     */
    VendingMachineItem purchaseItem(String itemName);

    /**
     * Remove the given item from the list of items
     * @param itemName the name of the item that is to be removed.
     * @return the item object that was removed from the list.
     */
    VendingMachineItem removeItem(String itemName);
}