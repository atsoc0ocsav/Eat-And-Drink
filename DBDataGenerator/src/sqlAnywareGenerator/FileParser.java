package sqlAnywareGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
	public ArrayList<Object> chargeConfigurationFile(String fileName) {
		ArrayList<Object> data = new ArrayList<>();
		if (fileName != null) {
			BufferedReader in = null;
			try {
				String sCurrentLine;
				in = new BufferedReader(new FileReader(fileName));
				while ((sCurrentLine = in.readLine()) != null) {
					if (!sCurrentLine.contains("#") && !sCurrentLine.isEmpty()) {
						data.add(sCurrentLine);
					}
				}
			} catch (IOException e) {
				System.err.println("Configuration File \"" + fileName
						+ "\" Not Found");
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException ex) {
					System.err.println("Error closing \"" + fileName + "\"");
				}
			}
		}
		return data; 
	}
}