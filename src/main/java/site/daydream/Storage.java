package site.daydream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.SortedMap;

import static site.daydream.Util.*;

/**
 * Author: Cymoo
 * Description: A thin wrapper around a `RandomAccessFile` object.
 * Date: 2021/5/21 12:17
 */
public class Storage {
    private final RandomAccessFile file;
    private static final int SUPERBLOCK_SIZE = 4096;
    private boolean closed;

    public Storage(String filename) throws IOException {
        file = new RandomAccessFile(filename, "rw");
        allocateSuperBlock();
        closed = false;
    }

    public byte[] read(long addr) throws IOException {
        file.seek(addr);
        int size = file.readInt();
        byte[] data = new byte[size];
        file.read(data);
        return data;
    }

    public long write(byte[] data) throws IOException {
        long end_addr = file.length();
        file.seek(end_addr);
        file.writeInt(data.length);
        file.write(data);
        return end_addr;
    }

    public void close() throws IOException {
        file.close();
        closed = true;
    }

    public boolean closed() {
        return closed;
    }

    public long getRootAddr() throws IOException {
        file.seek(0);
        return file.readLong();
    }

    public void setRootAddr(long addr) throws IOException {
        file.seek(0);
        file.writeLong(addr);
    }

    private void allocateSuperBlock() throws IOException {
        if (file.length() == 0) {
            file.write(new byte[SUPERBLOCK_SIZE]);
        }
    }


    private static void test1() throws IOException {
        Storage storage = new Storage("foo.db");
        long addr1 = storage.write(intToByteArray(13));
        long addr2 = storage.write(intToByteArray(31));

        int value1 = byteArrayToInt(storage.read(addr1));
        int value2 = byteArrayToInt(storage.read(addr2));
        System.out.printf("values: %d, %d\n", value1, value2);
    }

    private static void test2() throws IOException {
        Storage storage = new Storage("foo.db");
        int value2 = byteArrayToInt(storage.read(SUPERBLOCK_SIZE + 8));
        int value1 = byteArrayToInt(storage.read(SUPERBLOCK_SIZE));
        System.out.printf("value1: %d, %d\n", value1, value2);
    }

    public static void main(String[] args) throws IOException {
        Storage storage = new Storage("foo.db");
        System.out.println(storage.getRootAddr());
    }
}
