import java.sql.*;


public class DB {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mySchema", "root", "0208");
            if(!connection.isClosed()){
                System.out.println("Connected to MySql");
            }
            PreparedStatement findTickets = connection.prepareStatement("SELECT * FROM Ticket WHERE PASSENGER =?");
            findTickets.setInt(1, 1);
            ResultSet res = findTickets.executeQuery();
            while (res.next()){
                System.out.print(res.getString(1) + " ");
                System.out.print(res.getString(2) + " ");
                System.out.print(res.getString(3) + " ");
                System.out.println(res.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
