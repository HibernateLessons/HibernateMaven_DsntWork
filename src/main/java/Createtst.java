

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Createtst {
	
	@Id
	int    id;
	String name;

	public Createtst() {}

	public Createtst(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Createtst {id = " + String.valueOf(id) + ", name = '" + name + "'}";
	}	
	
	
	

}
