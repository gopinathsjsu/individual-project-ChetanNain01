package com.sample.model;
import java.util.HashMap;

public class Items {

    public HashMap<String, Item> itemMap = new HashMap<String, Item>();
    private int essentialCapValue = 5;
    private int luxaryCapValue = 3;
    private int miscCapValue = 6;
    public Items() {
        itemMap.put("Milk", new Item("Milk", 4.72, 6, "Essential"));
        itemMap.put("Bread", new Item("Bread", 2.98, 8, "Essential"));
        itemMap.put("Oil", new Item("Oil", 8.5, 2, "Essential"));
        itemMap.put("Cotton", new Item("Cotton", 11.2, 4, "Essential"));
        itemMap.put("Apples", new Item("Apples", 1, 8, "Essential"));
        itemMap.put("Chocolates", new Item("Chocolates", 3, 12, "Luxury"));
        itemMap.put("Honey", new Item("Honey", 4.2, 4, "Luxury"));
        itemMap.put("Cereal", new Item("Cereal", 6.34, 6, "Luxury"));
        itemMap.put("Soap", new Item("Soap", 5, 5, "Luxury"));
        itemMap.put("Detergent", new Item("Detergent", 3, 8, "Luxury"));
        itemMap.put("Paint", new Item("Paint", 7, 5, "Luxury"));
        itemMap.put("Pillow", new Item("Pillow", 6.76, 10, "Misc"));
        itemMap.put("Sheets", new Item("Sheets", 11.43, 13, "Misc"));
        itemMap.put("Blanket", new Item("Blanket", 45.5, 12, "Misc"));
        itemMap.put("Television", new Item("Television", 1100.54, 5, "Misc"));
        itemMap.put("Map", new Item("Map", 2.92, 2, "Misc")); 
    }

    public int getEssentialCapValue(){
        return essentialCapValue;
    }
    public int getLuxaryCapValue(){
        return luxaryCapValue;
    }
    public int getMiscCapValue(){
        return miscCapValue;
    }

    public String getCategory(String itemName){
        return itemMap.get(itemName).getCategory();
    }
}
