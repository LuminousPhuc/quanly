package quanlysach.business;

import java.util.Date;

public abstract class Book {
    protected String id;
    protected Date importDate;
    protected double unitPrice;
    protected int quantity;
    protected String publisher;

    public Book(String id, Date importDate, double unitPrice, int quantity, String publisher) {
        this.id = id;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public abstract double calculateTotalPrice();
    public abstract String getType();

    // Getters
    public String getId() { return id; }
    public Date getImportDate() { return importDate; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public String getPublisher() { return publisher; }
}
