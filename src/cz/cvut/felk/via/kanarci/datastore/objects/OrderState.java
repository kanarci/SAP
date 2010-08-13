package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public enum OrderState implements Serializable{
	OPEN, IN_PROGRESS, SHIPMENT, CLOSE
}
