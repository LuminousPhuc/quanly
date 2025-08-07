package quanlysach.presentation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import quanlysach.business.BookListViewUseCase;
import quanlysach.business.BookViewDTO;

public class BookListViewController {
    private BookViewModel model;
    private BookListViewUseCase listViewUseCase;

    public BookListViewController(BookViewModel model) {
        this.model = model;
    }

    public void setListViewUseCase(BookListViewUseCase listViewUseCase) {
        this.listViewUseCase = listViewUseCase;
    }

    public void execute() throws SQLException {
        List<BookViewDTO> dtoList = listViewUseCase.execute();
        List<BookViewItem> presenterList = convertToPresenter(dtoList);
        model.bookList = presenterList;
        model.notifySubscribers();
    }

    private List<BookViewItem> convertToPresenter(List<BookViewDTO> dtos) {
        List<BookViewItem> listViewItem = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (BookViewDTO dto : dtos) {
            BookViewItem item = new BookViewItem();
            item.stt = stt++;
            item.id = dto.id;
            // Định dạng ngày nhập
            item.importDate = fmt.format(dto.importDate); // Đổi tên trường cho rõ ràng hơn
            item.unitPrice = dto.unitPrice; // Thêm trường
            item.quantity = dto.quantity;   // Thêm trường
            item.publisher = dto.publisher; // Thêm trường
            item.type = dto.type;           // Thêm trường để hiển thị loại sách
            item.status = dto.status;       // Tình trạng sách giáo khoa hoặc "Không áp dụng" cho tham khảo
            item.totalPrice = dto.totalPrice; // Thêm trường

            listViewItem.add(item);
        }
        return listViewItem;
    }
}
