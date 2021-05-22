package site.daydream.binarytree;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static site.daydream.Util.*;

class Node {
    String key;
    ValueRef valueRef;
    NodeRef leftNodeRef;
    NodeRef rightNodeRef;
    int length;

    Node(String key, ValueRef valueRef, NodeRef leftNodeRef, NodeRef rightNodeRef, int length) {
        this.key = key;
        this.valueRef = valueRef;
        this.leftNodeRef = leftNodeRef;
        this.rightNodeRef = rightNodeRef;
        this.length = length;
    }

    static byte[] serialize(Node node) {
        return concatByteArray(
                longToByteArray(node.valueRef.addr),
                longToByteArray(node.leftNodeRef.addr),
                longToByteArray(node.rightNodeRef.addr),
                intToByteArray(node.length),
                node.key.getBytes(StandardCharsets.UTF_8)
        );
    }

    static Node deserialize(byte[] bytes) {
        long valueAddr = byteArrayToLong(Arrays.copyOfRange(bytes, 0, 8));
        long leftNodeAddr = byteArrayToLong(Arrays.copyOfRange(bytes, 8, 16));
        long rightNodeAddr = byteArrayToLong(Arrays.copyOfRange(bytes, 16, 24));
        int length = byteArrayToInt(Arrays.copyOfRange(bytes, 24, 28));
        String key = new String(Arrays.copyOfRange(bytes, 28, bytes.length), StandardCharsets.UTF_8);

        ValueRef valueRef = new ValueRef(valueAddr, null);
        NodeRef leftNodeRef = new NodeRef(leftNodeAddr, null);
        NodeRef rightNodeRef = new NodeRef(rightNodeAddr, null);

        return new Node(key, valueRef, leftNodeRef, rightNodeRef, length);
    }
}

