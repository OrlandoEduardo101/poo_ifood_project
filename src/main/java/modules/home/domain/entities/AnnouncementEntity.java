package modules.home.domain.entities;

import java.util.Map;

public class AnnouncementEntity {
    String title;
    String description;
    String sellerId;
    String sellerName;
    FoodEntity product;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
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

    public String toString(){
        return String.format("%s: %s%n%s: %s%n%s: %s%n%s: %s%n%s: %s", "Title", getTitle(), "Description", getDescription(), "Seller", getSellerName(), "Product:\n", product.toString());
    }

}
