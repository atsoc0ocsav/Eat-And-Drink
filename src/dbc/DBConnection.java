package dbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 

public class DBConnection {
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	// Acho q tou a ter problemas com o porto!
	public DBConnection(){
		try {
		conn = DriverManager.getConnection("jdbc:sqlanywhere:Tds:localhost:2638?eng=eatdrink","dba","sql");
		} catch (SQLException e) {
			System.out.println("Erro ao estabelecer a ligação");
		}
	}

	
	// Modelos de interação com a base de dados
	
//	public boolean inserirZona(Zona zona) {
//		try {
//			statement = conn
//					.prepareStatement("INSERT INTO Zona VALUES (?,?,?)");
//			statement.setInt(1, zona.getIdZona());
//			statement.setString(2, zona.getCidade());
//			statement.setString(3, zona.getDesignacao());
//			int result = statement.executeUpdate();
//			if (result == 1)
//				return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	public ArrayList<Zona> verZonas() {
//		ArrayList<Zona> lista = new ArrayList<Zona>();
//		try {
//			statement = conn.prepareStatement("SELECT * FROM Zona");
//			resultSet = statement.executeQuery();
//			while(resultSet.next()){
//				int idZona = resultSet.getInt("idZona");
//				String cidade = resultSet.getString("cidade");
//				String designacao = resultSet.getString("designacao");
//				Zona zona = new Zona(idZona, cidade, designacao);
//				lista.add(zona);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return lista;
//	}

}
