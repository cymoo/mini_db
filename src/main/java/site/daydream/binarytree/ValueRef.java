package site.daydream.binarytree;

import site.daydream.Storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

class ValueRef {
    long addr;
    String value;

    ValueRef(long addr, String value) {
        this.addr = addr;
        this.value = value;
    }

    String getValue(Storage storage) throws IOException {
        if (value != null) {
            return value;
        }
        byte[] bytes = storage.read(addr);
        value = new String(bytes, StandardCharsets.UTF_8);
        return value;
    }

    long save(Storage storage) throws IOException {
        if (addr != 0) {
            return addr;
        }
        addr = storage.write(value.getBytes(StandardCharsets.UTF_8));
        return addr;
    }
}
