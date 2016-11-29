package task_10;

import java.util.HashMap;

/**
 * Created by Dimon on 29.11.2016.
 */
public class ItemDataHolder {

    private String productName = "undefined";
    private String oldPrice = "undefined";
    private String newPrice = "undefined";
    private String oldPriceFontSize = "undefined";
    private String oldPriceTextDecoration = "undefined";
    private String oldPriceColor = "undefined";
    private String newPriceColor = "undefined";
    private String newPriceFontSize = "undefined";
    private String newPriceFontWeight = "undefined";

    public HashMap<String, String> toHashMap(){
        HashMap<String, String> result = new HashMap<>();
        result.put("PRODUCT_NAME", productName);
        result.put("OLD_PRICE", oldPrice);
        result.put("NEW_PRICE", newPrice);
        result.put("OLD_PRICE_FONT_SIZE", oldPriceFontSize);
        result.put("OLD_PRICE_TEXT_DECORATION", oldPriceTextDecoration);
        result.put("OLD_PRICE_COLOR", oldPriceColor);
        result.put("NEW_PRICE_COLOR", newPriceColor);
        result.put("NEW_PRICE_FONT_SIZE", newPriceFontSize);
        result.put("NEW_PRICE_FONT_WEIGHT", newPriceFontWeight);
        return result;
    }

    @Override
    public String toString() {
        return "ItemDataHolder{" +
                "productName='" + productName + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                ", newPrice='" + newPrice + '\'' +
                ", oldPriceFontSize='" + oldPriceFontSize + '\'' +
                ", oldPriceTextDecoration='" + oldPriceTextDecoration + '\'' +
                ", oldPriceColor='" + oldPriceColor + '\'' +
                ", newPriceColor='" + newPriceColor + '\'' +
                ", newPriceFontSize='" + newPriceFontSize + '\'' +
                ", newPriceFontWeight='" + newPriceFontWeight + '\'' +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOldPriceTextDecoration() {
        return oldPriceTextDecoration;
    }

    public void setOldPriceTextDecoration(String oldPriceTextDecoration) {
        this.oldPriceTextDecoration = oldPriceTextDecoration;
    }

    public String getOldPriceFontSize() {
        return oldPriceFontSize;
    }

    public void setOldPriceFontSize(String oldPriceFontSize) {
        this.oldPriceFontSize = oldPriceFontSize;
    }

    public String getOldPriceColor() {
        return oldPriceColor;
    }

    public void setOldPriceColor(String oldPriceColor) {
        this.oldPriceColor = oldPriceColor;
    }

    public String getNewPriceColor() {
        return newPriceColor;
    }

    public void setNewPriceColor(String newPriceColor) {
        this.newPriceColor = newPriceColor;
    }

    public String getNewPriceFontSize() {
        return newPriceFontSize;
    }

    public void setNewPriceFontSize(String newPriceFontSize) {
        this.newPriceFontSize = newPriceFontSize;
    }

    public String getNewPriceFontWeight() {
        return newPriceFontWeight;
    }

    public void setNewPriceFontWeight(String newPriceFontWeight) {
        this.newPriceFontWeight = newPriceFontWeight;
    }

    public String getMainPageProductName() {
        return productName;
    }

    public void setMainPageProductName(String mainPageProductName) {
        this.productName = mainPageProductName;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }
}
