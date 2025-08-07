package quanlysach.presentation;

public class BookViewItem {
    public int stt;             // Số thứ tự hiển thị
    public String id;           // Mã sách
    public String importDate;   // Ngày nhập (định dạng chuỗi để dễ hiển thị)
    public String type;         // Loại sách ("GiaoKhoa" hoặc "ThamKhao")
    public double unitPrice;    // Đơn giá
    public int quantity;        // Số lượng
    public String publisher;    // Nhà xuất bản
    public String status;       // Tình trạng (VD: "mới", "cũ", "Không áp dụng")
    public double totalPrice;   // Thành tiền đã tính sẵn
}

