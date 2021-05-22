package site.daydream.binarytree;

import site.daydream.Storage;

import java.io.IOException;

public class BinaryTree {
    private Storage storage;

    BinaryTree(Storage storage) {
        this.storage = storage;
    }

    String get(String key) throws IOException {
        return get(getRootNode(), key);
    }

    private Node getRootNode() throws IOException {
        return new NodeRef(storage.getRootAddr(), null).getNode(storage);
    }

    private String get(Node node, String key) throws IOException {
        if (node == null) {
            return null;
        }

        int cmp = node.key.compareTo(key);

        if (cmp > 0) {
            return get(node.leftNodeRef.getNode(storage), key);
        } else if (cmp < 0){
            return get(node.rightNodeRef.getNode(storage), key);
        } else {
            return node.valueRef.getValue(storage);
        }
    }

    void set(String key, String value) {

    }

    private void set(Node node, String key, String value) throws IOException {
        if (node == null) {
            ValueRef valueRef = new ValueRef(0, value);
            valueRef.save(storage);
            Node newNode = new Node(key, valueRef, null, null, 1);
        }
    }

    void del(String key) {

    }

    boolean has(String key) throws IOException {
        return get(key) != null;
    }
}
