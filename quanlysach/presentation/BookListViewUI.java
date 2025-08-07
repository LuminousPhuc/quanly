package quanlysach.presentation;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookListViewUI extends JFrame implements Subscriber {
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); // Có thể bỏ nếu đã định dạng ở Controller
    private BookViewModel viewModel;

    public BookListViewUI() {
        super("Danh sách sách thư viện");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 400); // Tăng kích thước để chứa nhiều cột hơn
        setLocationRelativeTo(null);

        // Table setup - Cập nhật các cột để khớp với BookViewItem và yêu cầu
        String[] cols = {"STT", "Mã sách", "Ngày Nhập", "Loại Sách", "Đơn Giá", "Số Lượng", "Nhà Xuất Bản", "Tình Trạng", "Thành Tiền"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setViewModel(BookViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.addSubscriber(this);
    }

    private void showList(BookViewModel bookViewModel) {
        model.setRowCount(0); // Xóa tất cả các hàng hiện có
        if (bookViewModel.bookList != null) { // Kiểm tra null để tránh lỗi
            for (BookViewItem item : bookViewModel.bookList) {
                Object[] row = {
                    item.stt,
                    item.id,
                    item.importDate,    // Ngày nhập đã được định dạng
                    item.type,          // Loại sách
                    item.unitPrice,     // Đơn giá
                    item.quantity,      // Số lượng
                    item.publisher,     // Nhà xuất bản
                    item.status,        // Tình trạng
                    item.totalPrice     // Thành tiền
                };
                model.addRow(row);
            }
        }
    }

    @Override
    public void update() {
        this.showList(viewModel);
    }
}
