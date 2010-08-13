package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public enum DeliveryMethod implements Serializable{
	PPL, DHL, CP, PERSONAL, MESSENGER
}
