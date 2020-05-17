package edu.hse.dkomshina.methods;

import edu.hse.dkomshina.datastructure.Node;
import edu.hse.dkomshina.datastructure.Triangle;
import javafx.scene.shape.TriangleMesh;

import java.util.ArrayList;

public class Methods {
    public Triangle[] triangles;

    final float radius = 50;
    public int iteration = 0;

    public TriangleMesh mesh;

    Methods(){}

    void addToMesh() {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();

        int index = 0;
        for (Triangle triangle : triangles) {

            nodes.add(triangle.rib1.a);
            indexes.add(index++);
            indexes.add(0);

            nodes.add(triangle.rib2.a);
            indexes.add(index++);
            indexes.add(0);

            nodes.add(triangle.rib3.a);
            indexes.add(index++);
            indexes.add(0);
        }

        float[] points = new float[nodes.size() * 3];
        int i = 0;
        for (Node node : nodes) {
            points[i] = node.x;
            points[i + 1] = node.y;
            points[i + 2] = node.z;
            i += 3;
        }

        int[] faces = new int[indexes.size()];
        i = 0;
        for (Integer ind : indexes) {
            faces[i] = ind;
            i++;
        }

        mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(0, 0);
        mesh.getFaces().addAll(faces);
    }

    public void nextIteration() {
        Triangle[] newTriangles = new Triangle[triangles.length * 4];
        int i = 0;
        for (Triangle triangle : triangles) {
            Triangle[] splitTriangles = triangle.getSplitTriangles(radius);
            newTriangles[i] = splitTriangles[0];
            newTriangles[i + 1] = splitTriangles[1];
            newTriangles[i + 2] = splitTriangles[2];
            newTriangles[i + 3] = splitTriangles[3];
            i += 4;
        }
        triangles = newTriangles;
        iteration++;
        addToMesh();
    }

    public void previousIteration() {
        Triangle[] newTriangles = new Triangle[triangles.length / 4];
        for (int j = 0, i = 0; j < newTriangles.length; j++, i += 4) {
            newTriangles[j] = triangles[i].parent;
        }
        triangles = newTriangles;
        iteration--;
        addToMesh();
    }

    private float SignedVolumeOfTriangle(Node p1, Node p2, Node p3) {
        var v321 = p3.x*p2.y*p1.z;
        var v231 = p2.x*p3.y*p1.z;
        var v312 = p3.x*p1.y*p2.z;
        var v132 = p1.x*p3.y*p2.z;
        var v213 = p2.x*p1.y*p3.z;
        var v123 = p1.x*p2.y*p3.z;
        return (1.0f/6.0f)*(-v321 + v231 + v312 - v132 - v213 + v123);
    }

    public float VolumeOfMesh() {
        float sum = 0;
        for (Triangle triangle : triangles){
            sum += SignedVolumeOfTriangle(triangle.rib1.a, triangle.rib2.a, triangle.rib3.a);
        }
        return Math.abs(sum);
    }
}
