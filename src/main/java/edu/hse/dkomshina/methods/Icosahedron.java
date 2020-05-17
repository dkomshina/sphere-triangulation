package edu.hse.dkomshina.methods;

import edu.hse.dkomshina.datastructure.Node;
import edu.hse.dkomshina.datastructure.Rib;
import edu.hse.dkomshina.datastructure.Triangle;

public class Icosahedron extends Methods {
    public Icosahedron() {

        double fi = (1 + Math.sqrt(5)) / 2;

        Node node = new Node((float) (fi * fi), 0, (float) (fi * fi * fi));
        double d = radius / node.length;

        Node[] nodes = new Node[12];
        nodes[0] = new Node((float) (fi * fi * d), 0, (float) (fi * fi * fi * d));
        nodes[1] = new Node(-(float) (fi * fi  * d), 0, (float) (fi * fi * fi * d));
        nodes[2] = new Node(0, (float) (fi * fi * fi * d), (float) (fi * fi * d));
        nodes[3] = new Node(0, -(float) (fi * fi * fi * d), (float) (fi * fi * d));
        nodes[4] = new Node((float) (fi * fi * fi * d), (float) (fi * fi * d), 0);
        nodes[5] = new Node(-(float) (fi * fi * fi * d), (float) (fi * fi * d), 0);
        nodes[6] = new Node(-(float) (fi * fi * fi * d), -(float) (fi * fi * d), 0);
        nodes[7] = new Node((float) (fi * fi * fi * d), -(float) (fi * fi * d), 0);
        nodes[8] = new Node(0, (float) (fi * fi * fi * d), -(float) (fi * fi * d));
        nodes[9] = new Node(0, -(float) (fi * fi * fi * d), -(float) (fi * fi * d));
        nodes[10] = new Node((float) (fi * fi * d), 0, -(float) (fi * fi * fi * d));
        nodes[11] = new Node(-(float) (fi * fi * d), 0, -(float) (fi * fi * fi * d));

        triangles = new Triangle[20];
        triangles[0] = new Triangle(new Rib(nodes[0], nodes[1]), new Rib(nodes[1], nodes[3]), new Rib(nodes[3], nodes[0]), null);
        triangles[1] = new Triangle(new Rib(nodes[0], nodes[2]), new Rib(nodes[2], nodes[1]), new Rib(nodes[1], nodes[0]), null);
        triangles[2] = new Triangle(new Rib(nodes[0], nodes[3]), new Rib(nodes[3], nodes[7]), new Rib(nodes[7], nodes[0]), null);
        triangles[3] = new Triangle(new Rib(nodes[0], nodes[7]), new Rib(nodes[7], nodes[4]), new Rib(nodes[4], nodes[0]), null);
        triangles[4] = new Triangle(new Rib(nodes[0], nodes[4]), new Rib(nodes[4], nodes[2]), new Rib(nodes[2], nodes[0]), null);
        triangles[5] = new Triangle(new Rib(nodes[7],nodes[10]),new Rib(nodes[10],nodes[4]),new Rib(nodes[4],nodes[7]),null);
        triangles[6] = new Triangle(new Rib(nodes[4],nodes[10]),new Rib(nodes[10],nodes[8]),new Rib(nodes[8],nodes[4]),null);
        triangles[7] = new Triangle(new Rib(nodes[4],nodes[8]),new Rib(nodes[8],nodes[2]),new Rib(nodes[2],nodes[4]),null);
        triangles[8] = new Triangle(new Rib(nodes[2],nodes[8]),new Rib(nodes[8],nodes[5]),new Rib(nodes[5],nodes[2]),null);
        triangles[9] = new Triangle(new Rib(nodes[2],nodes[5]),new Rib(nodes[5],nodes[1]),new Rib(nodes[1],nodes[2]),null);
        triangles[10] =new Triangle(new Rib(nodes[1],nodes[5]),new Rib(nodes[5],nodes[6]),new Rib(nodes[6],nodes[1]),null);
        triangles[11] =new Triangle(new Rib(nodes[1],nodes[6]),new Rib(nodes[6],nodes[3]),new Rib(nodes[3],nodes[1]),null);
        triangles[12] =new Triangle(new Rib(nodes[3],nodes[6]),new Rib(nodes[6],nodes[9]),new Rib(nodes[9],nodes[3]),null);
        triangles[13] =new Triangle(new Rib(nodes[3],nodes[9]),new Rib(nodes[9],nodes[7]),new Rib(nodes[7],nodes[3]),null);
        triangles[14] =new Triangle(new Rib(nodes[7],nodes[9]),new Rib(nodes[9],nodes[10]),new Rib(nodes[10],nodes[7]),null);
        triangles[15] =new Triangle(new Rib(nodes[11],nodes[10]),new Rib(nodes[10],nodes[9]),new Rib(nodes[9],nodes[11]),null);
        triangles[16] =new Triangle(new Rib(nodes[11],nodes[8]),new Rib(nodes[8],nodes[10]),new Rib(nodes[10],nodes[11]),null);
        triangles[17] =new Triangle(new Rib(nodes[11],nodes[5]),new Rib(nodes[5],nodes[8]),new Rib(nodes[8],nodes[11]),null);
        triangles[18] =new Triangle(new Rib(nodes[11],nodes[6]),new Rib(nodes[6],nodes[5]),new Rib(nodes[5],nodes[11]),null);
        triangles[19] =new Triangle(new Rib(nodes[11],nodes[9]),new Rib(nodes[9],nodes[6]),new Rib(nodes[6],nodes[11]),null);

        addToMesh();
    }
}
