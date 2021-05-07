package modules.home.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class MarketModel extends  FoodEntity{

    private Map<String, Float> productsList = new HashMap<String, Float>();

    public Map getProductsList() {
        return productsList;
    }

    public void setProductsList(Map productsList) {
        this.productsList = productsList;
    }

    public void fillProductsList(String name, float price) {
        this.productsList.put(name, price);
        float total = 0;

        for (Map.Entry<String, Float> entry : productsList.entrySet()) {
            String key = entry.getKey();
            float value = entry.getValue();
            total = total + value;
            //System.out.println(String.format("key: %s | value: %s", key, value));
        }

        setPrice("R$" + total);

    }

    @Override
    public String toString(){
        String listProduct = new String();
        for (Map.Entry<String, Float> entry : productsList.entrySet()) {
            String key = entry.getKey();
            float value = entry.getValue();
            listProduct.concat(key + ": " + value + "\n");
            //System.out.println(String.format("key: %s | value: %s", key, value));
        }
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s", "Product", super.getName(), "Quantity", super.getQuantity(), "Price", super.getPrice(), "items:\n", listProduct);
    }

    @Override
    public Map toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", super.name);
        map.put("quantity", super.quantity);
        map.put("price", super.price);
        map.put("productsList", this.productsList);
        return map;
    }

    public static FoodEntity fromMap(Map<String, Object> map){

        MarketModel model = new MarketModel();
        model.setName((String) map.get("name"));
        model.setPrice((String) map.get("price"));
        model.setQuantity((String) map.get("quantity"));
        model.setProductsList((Map) map.get("productsList"));

        return model;
    }
}
