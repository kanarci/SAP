package cz.cvut.felk.via.kanarci.datastore.utils;

public class DTRPCC {
	private static final DatastoreToRPCConversion dtrpccInstance = new DatastoreToRPCConversion();
    private DTRPCC() {}

    public static DatastoreToRPCConversion get() {
        return dtrpccInstance;
    }
}
