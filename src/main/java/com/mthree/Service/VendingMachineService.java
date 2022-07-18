package com.mthree.Service;

import java.util.HashMap;
import java.util.Map;

import com.mthree.DAO.Inventory;
import com.mthree.DTO.Coin;
import com.mthree.DTO.VendingMachineItem;
import com.mthree.Exception.InsufficientFundsException;
import com.mthree.Exception.NoItemInventoryException;

public class VendingMachineService {
    private Inventory inventory;

    public Map<Coin, Integer> money = new HashMap<Coin, Integer>();
    private double currentBalance = 0;

    public VendingMachineService(Inventory inventory){
        this.inventory = inventory;
        this.money = initMoneyMap();
    }

    private Map<Coin, Integer> initMoneyMap(){
        Map<Coin, Integer> money = new HashMap<Coin, Integer>();
        for (Coin coin : Coin.values()){
            money.put(coin, 0);
        }
        return money;
    }

    private Map<Coin, Integer> getChangeMap(double money){
        double target = money;
        Map<Coin, Integer> coins = initMoneyMap();

        for(Coin coin : Coin.values()){
            double coinValue = coin.getDenomination().doubleValue();
            while(target - coinValue>= 0){
                target -= coinValue;
                coins.put(coin, coins.get(coin) + 1);
            }
        }

        return coins;
    }

    public void insertCoins(double totalInserted){
        this.money = this.getChangeMap(totalInserted);
        this.currentBalance += totalInserted;
    }

    public Map<Coin, Integer> buyItem(VendingMachineItem item) throws InsufficientFundsException, NoItemInventoryException{
        if(item.getQuantity() > 0){
            double itemPrice = item.getItem().getPrice().doubleValue();
            if(this.currentBalance >= itemPrice){
                this.inventory.purchaseItem(item.getItem().getItemName());
                this.currentBalance -= itemPrice;
                return this.getChangeMap(this.currentBalance);
            }else{
                throw new InsufficientFundsException("Not enough money was inserted to purchase this item");
            } 
        }else{
            throw new NoItemInventoryException("Not enough quantity");
        }
    }

    public void stockItem(VendingMachineItem item, int quantity){
        item.setQuantity(item.getQuantity() + quantity);
        inventory.editItem(item.getItem().getItemName(), item);
    }
}
