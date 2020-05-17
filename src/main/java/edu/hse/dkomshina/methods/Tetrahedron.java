package edu.hse.dkomshina.methods;

import edu.hse.dkomshina.datastructure.*;

public class Tetrahedron extends Methods {

    public Tetrahedron() {

        float tetrahedronRibLength = (float) ((4 / Math.sqrt(6)) * radius);
        float tetrahedronHeightLength = (float) (Math.sqrt(2.0 / 3.0) * tetrahedronRibLength);
        float distanceY1 = (float) (tetrahedronRibLength / Math.sqrt(3));
        float distanceY2 = (float) (tetrahedronRibLength / (2 * Math.sqrt(3)));

        Node[] nodes = new Node[4];
        nodes[0] = new Node(0, 0, -radius);
        nodes[1] = new Node(0, -distanceY1, tetrahedronHeightLength - radius);
        nodes[2] = new Node(tetrahedronRibLength / 2, distanceY2, tetrahedronHeightLength - radius);
        nodes[3] = new Node(-tetrahedronRibLength / 2, distanceY2, tetrahedronHeightLength - radius);

        triangles = new Triangle[4];
        triangles[0] = new Triangle(new Rib(nodes[1], nodes[2]), new Rib(nodes[2], nodes[3]), new Rib(nodes[3], nodes[1]), null);
        triangles[1] = new Triangle(new Rib(nodes[0], nodes[1]), new Rib(nodes[1], nodes[3]), new Rib(nodes[3], nodes[0]), null);
        triangles[2] = new Triangle(new Rib(nodes[0], nodes[3]), new Rib(nodes[3], nodes[2]), new Rib(nodes[2], nodes[0]), null);
        triangles[3] = new Triangle(new Rib(nodes[0], nodes[2]), new Rib(nodes[2], nodes[1]), new Rib(nodes[1], nodes[0]), null);

        addToMesh();
    }
}
