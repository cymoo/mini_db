package site.daydream.binarytree;

import site.daydream.Storage;

import java.io.IOException;

class NodeRef {
    long addr;
    Node node;

    NodeRef(long addr, Node node) {
        this.addr = addr;
        this.node = node;
    }

   Node getNode(Storage storage) throws IOException {
        if (node != null) {
            return node;
        }
        byte[] bytes = storage.read(addr);
        node = Node.deserialize(bytes);
        return node;
    }

    long save(Storage storage) throws IOException {
        if (addr != 0) {
            return addr;
        }
        addr = storage.write(Node.serialize(node));
        return addr;
    }
}

