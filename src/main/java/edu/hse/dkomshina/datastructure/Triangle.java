package edu.hse.dkomshina.datastructure;

public class Triangle {
    public Rib rib1, rib2, rib3;
    public Triangle parent;

    public Triangle(Rib a, Rib b, Rib c, Triangle parent){
        this.rib1 = a;
        this.rib2 = b;
        this.rib3 = c;
        this.parent = parent;
    }

    public Triangle[] getSplitTriangles(float r){
        Node midNodeA = rib1.midNode;
        Node midNodeB = rib2.midNode;
        Node midNodeC = rib3.midNode;

        Node newA = new Node (midNodeA.x * r / midNodeA.length, midNodeA.y * r / midNodeA.length, midNodeA.z * r / midNodeA.length);
        Node newB = new Node (midNodeB.x * r / midNodeB.length, midNodeB.y * r / midNodeB.length, midNodeB.z * r / midNodeB.length);
        Node newC = new Node (midNodeC.x * r / midNodeC.length, midNodeC.y * r / midNodeC.length, midNodeC.z * r / midNodeC.length);

        Triangle[] triangles = new Triangle[4];

        triangles[0] = new Triangle (new Rib (rib1.a, newA), new Rib (newA, newC), new Rib (newC, rib1.a), this);
        triangles[1] = new Triangle (new Rib (newA, rib2.a), new Rib (rib2.a, newB), new Rib (newB, newA), this);
        triangles[2] = new Triangle (new Rib (newA, newB), new Rib (newB, newC), new Rib (newC, newA), this);
        triangles[3] = new Triangle (new Rib (rib3.a, newC), new Rib (newC, newB), new Rib (newB, rib3.a), this);

        return triangles;
    }
}
