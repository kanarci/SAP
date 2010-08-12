package cz.cvut.felk.via.kanarci.datastore.utils;

public final class DUF {
    private static final DatastoreUtil duInstance = new DatastoreUtil();
    private DUF() {}

    public static DatastoreUtil get() {
        return duInstance;
    }
}