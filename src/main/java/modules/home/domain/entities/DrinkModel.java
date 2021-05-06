package modules.home.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class DrinkModel extends FoodEntity {
    private boolean isAchoolic = false;

    public boolean isAchoolic() {
        return isAchoolic;
    }

    public void setAchoolic(boolean achoolic) {
        isAchoolic = achoolic;
    }

    @Override
    public String toString(){
            String achoolic = isAchoolic ? "Yes" : "No";

        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s", "Product", super.getName(), "Quantity", super.getQuantity(), "Price", super.getPrice(), "Achoolic:", achoolic);
    }

    @Override
    public Map toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", super.name);
        map.put("quantity", super.quantity);
        map.put("price", super.price);
        map.put("isAchoolic", this.isAchoolic);
        return map;
    }

    public static FoodEntity fromMap(Map<String, Object> map){

        DrinkModel model = new DrinkModel();
        model.setName((String) map.get("name"));
        model.setPrice((String) map.get("price"));
        model.setQuantity((String) map.get("quantity"));
        model.setAchoolic((Boolean) map.get("isAchoolic"));

        return model;
    }


}
