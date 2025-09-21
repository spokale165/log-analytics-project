package com.loganalytics.backend.dto;

public class OrderRequest {
    private String productId;
    private int quantity;
    private double pricePerUnit;

    public OrderRequest() { }

    public OrderRequest(String productId, int quantity, double pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}
