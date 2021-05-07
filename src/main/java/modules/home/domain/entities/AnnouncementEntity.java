package modules.home.domain.entities;

import java.util.HashMap;
import java.util.Map;

public class AnnouncementEntity {
    private String title;

    public AnnouncementEntity(int id, String title, String description, int sellerId, String productCode, String sellerName, FoodEntity product) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.sellerId = sellerId;
        this.productCode = productCode;
        this.sellerName = sellerName;
        this.product = product;
    }

    public AnnouncementEntity(){};

    private String description;
    private int sellerId;
    private int id;
    private String productCode;
    private String sellerName;
    private FoodEntity product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String code) {
        this.productCode = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public FoodEntity getProduct() {
        return product;
    }

    public void setProduct(FoodEntity product) {
        this.product = product;
    }

    public String getProductDetail() {
        return product.toString();
    }

    public String toString(){
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s", "Title", getTitle(), "Description", getDescription(), "Seller", getSellerName(), "Price", product.getPrice(), "Product Code", getProductCode());
    }

    public Map toMap(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", this.title);
        map.put("id", this.id);
        map.put("productCode", this.productCode);
        map.put("description", this.description);
        map.put("sellerName", this.sellerName);
        map.put("product", this.product);
        map.put("sellerId", this.sellerId);
        return map;
    }

    public static AnnouncementEntity fromMap(Map<String, Object> map){

        AnnouncementEntity model = new AnnouncementEntity();
        model.setTitle((String) map.get("title"));
        model.setId((int) map.get("id"));
        model.setSellerId((int) map.get("sellerId"));
        model.setDescription((String) map.get("description"));
        model.setProductCode((String) map.get("productCode"));
        model.setSellerName((String) map.get("sellerName"));
        model.setProduct((FoodEntity) map.get("product"));

        return model;
    }

}
