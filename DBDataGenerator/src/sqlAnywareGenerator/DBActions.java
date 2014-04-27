package sqlAnywareGenerator;

import java.util.ArrayList;

public class DBActions {
	public enum Operation_Type {
		ADD_CITY, ADD_ZONE
	};
	
	private FileParser parser=new FileParser();

	public void addCitiesToDB() {
		ArrayList<String> cities=(ArrayList<String>)parser.parseFile("/data/Lista de Cidades.txt");
		for(String string : cities)
			System.out.println(string);
	}

}
