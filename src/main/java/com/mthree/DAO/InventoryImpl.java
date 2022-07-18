package com.mthree.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mthree.DTO.Item;
import com.mthree.DTO.VendingMachineItem;

public class InventoryImpl implements Inventory{

    private Map<String, VendingMachineItem> items = new HashMap<String, VendingMachineItem>();
    private String ITEM_FILE = "items.txt";

    @Override
    public List<VendingMachineItem> getItems() {
        loadItems();
        return new ArrayList<VendingMachineItem>(this.items.values());
    }

    @Override
    public VendingMachineItem getItem(String itemName) {
        loadItems();
        VendingMachineItem item = null;

        for(VendingMachineItem currentItem : this.items.values()){
            if(currentItem.getItem().getItemName().equals(itemName)){
                return currentItem;
            }
        }
        return item;
    }

    @Override
    public VendingMachineItem addNewItem(String itemName, VendingMachineItem newItem) {
        loadItems();
        VendingMachineItem item = items.put(itemName, newItem);
        writeItems();
        return item;
    }

    @Override
    public VendingMachineItem purchaseItem(String itemName) {
        loadItems();
        VendingMachineItem item = this.getItem(itemName);
        item.setQuantity(item.getQuantity() - 1);
        writeItems();
        return item;
    }

    @Override
    public VendingMachineItem editItem(String itemName, VendingMachineItem editedItem) {
        loadItems();
        VendingMachineItem item = this.items.put(itemName, editedItem);
        writeItems();
        return item;
    }

    @Override
    public VendingMachineItem removeItem(String itemName) {
        loadItems();
        VendingMachineItem removedItem = this.items.remove(itemName);
        writeItems();
        return removedItem;
    }

    /**
     * Helper method to convert input text from the file to an item object.
     * @param itemAsText the line taken from the items file containing all information
     * needed to create a new item.
     * @return a vendingmachineitem object consisting of the data from the input file.
     */
    private VendingMachineItem unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(",");

        String itemName = itemTokens[0];
        BigDecimal price = new BigDecimal(itemTokens[1]);
        int quantity = Integer.parseInt(itemTokens[2]);

        VendingMachineItem item = new VendingMachineItem(new Item(itemName, price), quantity);
        return item;
    }

    /**
     * Function to read the items file and save it to the map.
     */
    private void loadItems(){
        Scanner input = null;

        try {
            input = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine;
        VendingMachineItem currentItem;

        while(input.hasNextLine()){
            currentLine = input.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getItem().getItemName(), currentItem);
        }

        input.close();
    }

    /**
     * Helper method that converts item data into a string and returns it.
     * @param item the item object we want to convert to a string.
     * @return a string containing data from the item object.
     */
    private String marshallItem(VendingMachineItem item){
        String itemText = item.getItem().getItemName() + ",";
        itemText += item.getItem().getPrice() + ",";
        itemText += item.getQuantity();

        return itemText;
    }

    /**
     * Function to write the data from a map to the items file.
     */
    private void writeItems(){
        PrintWriter output = null;

        try {
            output = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String itemAsText;
        List<VendingMachineItem> itemList = this.getItems();

        for(VendingMachineItem item : itemList){
            itemAsText = marshallItem(item);
            output.println(itemAsText);
            output.flush();
        }

        output.close();
    }

}