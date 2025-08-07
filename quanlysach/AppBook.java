package quanlysach;

import java.sql.SQLException;
import quanlysach.business.BookListViewUseCase;
import quanlysach.persistence.BookListViewDAO;
import quanlysach.presentation.BookListViewController;
import quanlysach.presentation.BookListViewUI;
import quanlysach.presentation.BookViewModel;

public class AppBook {

    public static void main(String[] args) {
        BookListViewUI view = new BookListViewUI();
        BookListViewController controller = null;
        BookViewModel model = new BookViewModel();
        view.setViewModel(model);
        BookListViewUseCase listViewUseCase = null;

        try {
            BookListViewDAO listViewDAO = new BookListViewDAO();
            listViewUseCase = new BookListViewUseCase(listViewDAO);
            controller = new BookListViewController(model);
            controller.setListViewUseCase(listViewUseCase);

            controller.execute();
            view.setVisible(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
