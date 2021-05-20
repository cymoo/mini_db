package site.daydream;

import java.io.IOException;

public class Database {
    private final Storage storage;

    public static Database connect(String dbFile) {
        return null;
    }

    public String get(String key) {
        return null;
    }

    public void set(String key, String value) {}

    public void del(String key) {}

    public boolean has(String key) {
        return false;
    }

    public int size() {
        return 0;
    }

    public void close() throws IOException {
        storage.close();
    }

    private Database(String dbFile) throws IOException {
        storage = new Storage(dbFile);
    }
}
