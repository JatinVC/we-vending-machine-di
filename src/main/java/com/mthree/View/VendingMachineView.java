package com.mthree.View;

import java.util.List;
import java.util.Map;

import com.mthree.DTO.Coin;
import com.mthree.DTO.VendingMachineItem;

public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io){
        this.io = io;
    }

    /**
     * print the menu and return the users input
     * @return an integer signifying the users choice.
     */
    public int printMenuAndGetSelection(List<VendingMachineItem> itemList){
        io.print("Vending Machine Items");
        this.printAllItems(itemList);
        io.print("Main Menu");
        io.print("1. Enter Money");
        io.print("2. Buy Item");
        io.print("3. Restock Item");
        io.print("4. Exit Program");

        return io.readInt("Please select from the above choices", 1, 4);
    }

    /**
     * Print the list of all items in the system.
     * @param itemList the list of all items in the system.
     */
    public void printAllItems(List<VendingMachineItem> itemList){
        for(VendingMachineItem item : itemList){
            this.printItemInfo(item);
        }
    }

    /**
     * Prints the information about an item
     * @param item the item that we need to display
     */
    public void printItemInfo(VendingMachineItem item){
        String itemString = item.getItem().getItemName() + ":";
        itemString += item.getItem().getPrice();
        io.print(itemString); 
    }

    /**
     * Get the money inputted to the system.
     * @return the amount the user inserted.
     */
    public double getMoneyInput(){
        return io.readDouble("Please enter the amount you would like to put into machine");
    }

    /**
     * The name of the item.
     * @return the user input.
     */
    public String chooseItem(){
        return io.readString("Please enter the name of the item");
    }

    public int stockUpItemMenu(){
        return io.readInt("Please enter the amount you would like to increase this item by");
    }

    public int printEditMenuAndGetSelection(){
        io.print("Edit Menu");
        io.print("1. Edit Item Name");
        io.print("2. Edit Item Price");
        io.print("3. Edit Item Quantity");
        io.print("4. Exit Edit Menu");

        return io.readInt("Please select from the above options", 1, 4);
    }

    public String printEditName(){
        io.print("Edit Item Name");
        return io.readString("Please enter the new name");
    }

    public float printEditPrice(){
        io.print("Edit Item Price");    
        return io.readFloat("Please enter the new price");
    }

    public void printError(String msg){
        io.print(msg);
        io.readString("Please press ENTER to continue");
    }

    public void printChange(Map<Coin, Integer> change){
        System.out.println(change);
    }
}
