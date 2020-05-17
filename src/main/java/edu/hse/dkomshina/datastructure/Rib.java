package edu.hse.dkomshina.datastructure;

public class Rib {
    public Node a, b, midNode;

    public Rib(Node a, Node b) {
        this.a = a;
        this.b = b;
        midNode = new Node(a.x + (b.x - a.x) / 2, a.y + (b.y - a.y) / 2, a.z + (b.z - a.z) / 2);
    }
}
