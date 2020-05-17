package edu.hse.dkomshina.datastructure;

public class Node {
    public float x, y, z, length;

    public Node(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = (float) Math.sqrt (x*x + y*y + z*z);
    }
}
