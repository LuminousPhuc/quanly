package quanlysach.business;

import java.util.Date;

public class BookViewDTO {
    public String id;
    public Date importDate;
    public double unitPrice;
    public int quantity;
    public String publisher;
    public String type;         // "GiaoKhoa" hoặc "ThamKhao"
    public String status;       // Chỉ dùng cho sách giáo khoa: "mới" hoặc "cũ"
    public Double tax;          // Chỉ dùng cho sách tham khảo
    public double totalPrice;   // Thành tiền đã tính sẵn
}