package quanlysach.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quanlysach.persistence.BookDTO;
import quanlysach.persistence.BookListViewDAO;

public class BookListViewUseCase {
    private BookListViewDAO listViewDAO;

    public BookListViewUseCase(BookListViewDAO listViewDAO) {
        this.listViewDAO = listViewDAO;
    }

    public List<BookViewDTO> execute() throws SQLException {
        List<BookDTO> listDTO = listViewDAO.getAll();
        List<Book> books = convertToBusinessObjects(listDTO);
        return convertToViewDTO(books);
    }

    private List<Book> convertToBusinessObjects(List<BookDTO> dtos) {
        List<Book> books = new ArrayList<>();
        for (BookDTO dto : dtos) {
            if ("Textbook".equalsIgnoreCase(dto.type)) {
                books.add(new Textbook(
                    dto.id, dto.importDate, dto.unitPrice,
                    dto.quantity, dto.publisher, dto.status
                ));
            } else if ("Reference".equalsIgnoreCase(dto.type)) {
                // Đảm bảo tax không null khi tạo ReferenceBook
                books.add(new ReferenceBook(
                    dto.id, dto.importDate, dto.unitPrice,
                    dto.quantity, dto.publisher, dto.tax != null ? dto.tax : 0.0
                ));
            }
        }
        return books;
    }

    private List<BookViewDTO> convertToViewDTO(List<Book> books) {
        List<BookViewDTO> viewList = new ArrayList<>();
        for (Book book : books) {
            BookViewDTO dto = new BookViewDTO();
            dto.id = book.getId();
            dto.importDate = book.getImportDate();
            dto.unitPrice = book.getUnitPrice(); // Gán giá trị
            dto.quantity = book.getQuantity();   // Gán giá trị
            dto.publisher = book.getPublisher(); // Gán giá trị
            dto.totalPrice = book.calculateTotalPrice(); // Gán giá trị

            if (book instanceof Textbook) {
                Textbook textbook = (Textbook) book;
                dto.type = "GiaoKhoa";
                dto.status = textbook.getStatus();
                dto.tax = null; // Sách giáo khoa không có thuế
            } else if (book instanceof ReferenceBook) {
                ReferenceBook referenceBook = (ReferenceBook) book;
                dto.type = "ThamKhao";
                dto.status = "Không áp dụng"; // Hoặc một giá trị phù hợp khác cho sách tham khảo
                dto.tax = referenceBook.getTax();
            }
            viewList.add(dto);
        }
        return viewList;
    }
}
