package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Team implements Serializable{


	private static final long serialVersionUID = -4113834555548422813L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private List<Employee> employeesInTeam;

	public Team(String name) {
		super();
		this.name = name;
		this.employeesInTeam = new ArrayList<Employee>();
	}

	public Team(String name, List<Employee> employeesInTeam) {
		super();
		this.name = name;
		this.employeesInTeam = employeesInTeam;
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getemployeesInTeam() {
		return employeesInTeam;
	}

	public void setemployeEsInTeam(List<Employee> employeesInTeam) {
		this.employeesInTeam = employeesInTeam;
	}

	public void removeEmployeesInTeam() {
		this.employeesInTeam.removeAll(getemployeesInTeam());
	}

	public void addEmployee(Employee em) {
		this.employeesInTeam.add(em);
	}

	public void removeEmployee(Employee em) {
		this.employeesInTeam.remove(employeesInTeam.indexOf(em));
	}

}
