package modules.home.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class MealModel extends  FoodEntity {
    private boolean hasGluten = false;

    public boolean hasGluten() {
        return hasGluten;
    }

    public void setGluten(boolean gluten) {
        hasGluten = gluten;
    }

    @Override
    public String toString(){
        String gluten = hasGluten ? "Yes" : "No";

        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s", "Product", super.getName(), "Quantity", super.getQuantity(), "Price", super.getPrice(), "Glúten:", gluten);
    }

    @Override
    public Map toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", super.name);
        map.put("quantity", super.quantity);
        map.put("price", super.price);
        map.put("hasGluten", this.hasGluten);
        return map;
    }

    public static FoodEntity fromMap(Map<String, Object> map){

        MealModel model = new MealModel();
        model.setName((String) map.get("name"));
        model.setPrice((String) map.get("price"));
        model.setQuantity((String) map.get("quantity"));
        model.setGluten((Boolean) map.get("hasGluten"));

        return model;
    }
}
