package quanlysach.business;

import java.util.Date;

public class Textbook extends Book {
    private String status; // "mới" hoặc "cũ"

    public Textbook(String id, Date importDate, double unitPrice, int quantity, String publisher, String status) {
        super(id, importDate, unitPrice, quantity, publisher);
        this.status = status.toLowerCase();
    }

    @Override
    public double calculateTotalPrice() {
        if (status.equals("mới")) {
            return quantity * unitPrice;
        } else if (status.equals("cũ")) {
            return quantity * unitPrice * 0.5;
        } else {
            return 0.0;
        }
    }

    @Override
    public String getType() {
        return "Sách giáo khoa";
    }

    public String getStatus() {
        return status;
    }
}
