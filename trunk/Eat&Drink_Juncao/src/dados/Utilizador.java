package dados;

import java.util.LinkedList;

public class Utilizador {
	
	private String email;
	private int idPhoto;
	private String name;
	private String school;
	private String city;
	private String zone;
	private String password;
	private LinkedList<String> followers = new LinkedList<String>();
	
	public Utilizador(String email, int idPhoto, String name, String school, String city, String zone, String password) {
		this.email = email;
		this.idPhoto = idPhoto;
		this.name = name;
		this.school = school;
		this.city = city;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCity() {
		return city;
	}
	
	public String getZone() {
		return zone;
	}
	
	public void setZone(String zone){
		this.zone = zone;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LinkedList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(LinkedList<String> followers) {
		this.followers = followers;
	}
}
