package com.mthree.Controller;

import com.mthree.DAO.Inventory;
import com.mthree.DTO.VendingMachineItem;
import com.mthree.Exception.InsufficientFundsException;
import com.mthree.Exception.NoItemInventoryException;
import com.mthree.Service.VendingMachineService;
import com.mthree.View.VendingMachineView;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineService service;
    private Inventory inventory;

    public VendingMachineController(VendingMachineView view, VendingMachineService service, Inventory inventory){
        this.view = view;
        this.service = service;
        this.inventory = inventory;
    }

    public void run(){
        int menuSelection = 0;
        while(menuSelection != 4){
            menuSelection = getMenuSelection();

            switch(menuSelection){
                case 1:
                    insertCoins();
                    break;
                case 2:
                    buyItem();
                    break;
                case 3:
                    restockItem();
                    break;
            }
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection(inventory.getItems());
    }

    private void insertCoins(){
        service.insertCoins(view.getMoneyInput());
    }

    private void buyItem(){
        String itemName = view.chooseItem();
        VendingMachineItem item = inventory.getItem(itemName);
        try{
            view.printChange(service.buyItem(item));
        }catch(NoItemInventoryException ne){
            view.printError("Not enough quantity for this item");
        }catch(InsufficientFundsException ie){
            view.printError("Not enough money was inserted");
        }
    }

    private void restockItem(){
        String itemToStock = view.chooseItem();
        int quantityToAdd = view.stockUpItemMenu();
        VendingMachineItem stockedItem = inventory.getItem(itemToStock);
        service.stockItem(stockedItem, quantityToAdd);
    }
}
