
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class connect {
    public static Connection db_connect()
    {
        try
        {
            Connection con=null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (Connection) DriverManager.getConnection("Jdbc:mysql://localhost:3306/shopping_mall","root","");
            return con;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
