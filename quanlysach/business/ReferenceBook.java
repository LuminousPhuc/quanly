package quanlysach.business;

import java.util.Date;

public class ReferenceBook extends Book {
    private double tax;

    public ReferenceBook(String id, Date importDate, double unitPrice, int quantity, String publisher, double tax) {
        super(id, importDate, unitPrice, quantity, publisher);
        this.tax = tax;
    }

    @Override
    public double calculateTotalPrice() {
        return quantity * unitPrice + tax;
    }

    @Override
    public String getType() {
        return "Sách tham khảo";
    }

    public double getTax() {
        return tax;
    }
}