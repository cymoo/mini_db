package site.daydream;

import java.nio.ByteBuffer;

public class Util {
    private Util() {}

    public static byte[] intToByteArray(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public static byte[] longToByteArray(long value) {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    public static int byteArrayToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static long byteArrayToLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getLong();
    }

    public static byte[] concatByteArray(byte[]... arrays) {
        int len = 0;
        for (byte[] array: arrays) {
            len += array.length;
        }

        byte[] dest = new byte[len];

        int idx = 0;
        for (byte[] array: arrays) {
            System.arraycopy(array, 0, dest, idx, array.length);
            idx += array.length;
        }

        return dest;
    }
}
