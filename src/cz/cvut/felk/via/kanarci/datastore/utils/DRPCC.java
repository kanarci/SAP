package cz.cvut.felk.via.kanarci.datastore.utils;

public class DRPCC {
	private static final DatastoreRPCConversion drpccInstance = new DatastoreRPCConversion();
    private DRPCC() {}

    public static DatastoreRPCConversion get() {
        return drpccInstance;
    }
}
