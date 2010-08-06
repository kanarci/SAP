package cz.cvut.felk.via.kanarci.datastore.objects;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
// (detachable="true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Person {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Contact contactInfo;

	public Person(Contact contact) {
		super();
		this.contactInfo = contact;
	}

	public Key getKey() {
		return key;
	}

	public Contact getContact() {
		return contactInfo;
	}

	public void setContact(Contact contact) {
		this.contactInfo = contact;
	}

	@Override
	public String toString() {
		return this.contactInfo.toString();
	}
}