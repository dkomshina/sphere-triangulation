package edu.hse.dkomshina.methods;

import edu.hse.dkomshina.datastructure.Node;
import edu.hse.dkomshina.datastructure.Rib;
import edu.hse.dkomshina.datastructure.Triangle;

public class Octahedron extends Methods {

    public Octahedron(){

        Node[] nodes = new Node[6];
        nodes[0] = new Node(0, radius, 0);
        nodes[1] = new Node(radius, 0, 0);
        nodes[2] = new Node(0, 0, -radius);
        nodes[3] = new Node(-radius, 0, 0);
        nodes[4] = new Node(0, 0, radius);
        nodes[5] = new Node(0, -radius, 0);

        triangles = new Triangle[8];
        triangles[0] = new Triangle(new Rib(nodes[0], nodes[1]), new Rib(nodes[1], nodes[2]), new Rib(nodes[2], nodes[0]), null);
        triangles[1] = new Triangle(new Rib(nodes[0], nodes[2]), new Rib(nodes[2], nodes[3]), new Rib(nodes[3], nodes[0]), null);
        triangles[2] = new Triangle(new Rib(nodes[0], nodes[3]), new Rib(nodes[3], nodes[4]), new Rib(nodes[4], nodes[0]), null);
        triangles[3] = new Triangle(new Rib(nodes[0], nodes[4]), new Rib(nodes[4], nodes[1]), new Rib(nodes[1], nodes[0]), null);
        triangles[4] = new Triangle(new Rib(nodes[1], nodes[5]), new Rib(nodes[5], nodes[2]), new Rib(nodes[2], nodes[1]), null);
        triangles[5] = new Triangle(new Rib(nodes[2], nodes[5]), new Rib(nodes[5], nodes[3]), new Rib(nodes[3], nodes[2]), null);
        triangles[6] = new Triangle(new Rib(nodes[3], nodes[5]), new Rib(nodes[5], nodes[4]), new Rib(nodes[4], nodes[3]), null);
        triangles[7] = new Triangle(new Rib(nodes[4], nodes[5]), new Rib(nodes[5], nodes[1]), new Rib(nodes[1], nodes[4]), null);

        addToMesh();
    }
}
