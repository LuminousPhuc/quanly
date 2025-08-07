package quanlysach.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookListViewDAO {

    private Connection conn;

    public BookListViewDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/book?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "phuc1234";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to DB!");
    }

    public List<BookDTO> getAll() throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Sach";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            BookDTO dto = new BookDTO();
            dto.id = rs.getString("MaSach");
            dto.importDate = rs.getDate("NgayNhap");
            dto.unitPrice = rs.getDouble("DonGia");
            dto.quantity = rs.getInt("SoLuong");
            dto.publisher = rs.getString("NhaXuatBan");
            dto.type = rs.getString("LoaiSach");
            dto.status = rs.getString("TinhTrang"); // null nếu là ThamKhao
            dto.tax = rs.getObject("Thue") != null ? rs.getDouble("Thue") : null;

            list.add(dto);
        }
        rs.close();
        stmt.close();
        conn.close();

        return list;
    }

    public static void main(String[] args) {
        try {
            BookListViewDAO dao = new BookListViewDAO();
            List<BookDTO> books = dao.getAll();
            for (BookDTO b : books) {
                System.out.println(b.id + " - " + b.type + " - " + b.unitPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
