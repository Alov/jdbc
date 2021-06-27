import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost/elib_test";
        Properties props = new Properties();
        props.setProperty("user","elib_user");
        props.setProperty("password","elib123");
        props.setProperty("characterEncoding","UTF-8");
        Connection conn = DriverManager.getConnection(url, props);


        String testString = "'test','test'); DROP table test";

        PreparedStatement ps = conn.prepareStatement("insert into test (id, name, position) values (?, ?, ?)");
        ps.setInt(1, 9);
        ps.setString(2, testString);
        ps.setString(3, "test");
        boolean execute = ps.execute();

        Statement statement = conn.createStatement();
        boolean hasResult = statement.execute("select * from test");

        if (execute) {
            System.out.println("success insert");
        }

        if(hasResult) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next() != false) {

                User U = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                users.add(U);
            }
            users.forEach(it -> System.out.println(it.toString()));
        }

    }

}
