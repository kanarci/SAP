package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;

public enum OrderState implements Serializable{
	OPEN, IN_PROGRESS, SHIPMENT, CLOSE
}
