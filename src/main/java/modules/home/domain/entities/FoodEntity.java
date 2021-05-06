package modules.home.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class FoodEntity {
    String name;
    String quantity;
    String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String toString(){
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s", "Food", getName(), "Quantity", getQuantity(), "Price", getPrice());
    }

    public Map toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", this.name);
        map.put("quantity", this.quantity);
        map.put("price", this.price);
        return map;
    }

    public static FoodEntity fromMap(Map<String, Object> map){

        FoodEntity model = new FoodEntity();
        model.setName((String) map.get("name"));
        model.setPrice((String) map.get("price"));
        model.setQuantity((String) map.get("quantity"));

        return model;
    }



}
