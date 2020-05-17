package edu.hse.dkomshina;

import edu.hse.dkomshina.methods.Icosahedron;
import edu.hse.dkomshina.methods.Methods;
import edu.hse.dkomshina.methods.Octahedron;
import edu.hse.dkomshina.methods.Tetrahedron;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Triangulation extends Application {

    private final Group axisGroup = new Group();
    private final Group pyramidGroup = new Group();
    private final Group sphereGroup = new Group();
    private final Group world = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private double sceneWidth = 800;
    private double sceneHeight = 600;

    private Methods sphere;
    private MeshView sphereMeshView;

    private final Text iteration = new Text();
    private final Text triangles = new Text();
    private final Text volume = new Text();


    private void buildAxes() {
        final PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKBLUE);
        material.setSpecularColor(Color.BLUE);

        Box xAxis = new Box(240.0, 0.3, 0.3);
        Box yAxis = new Box(0.3, 240.0, 0.3);
        Box zAxis = new Box(0.3, 0.3, 240.0);

        xAxis.setMaterial(material);
        yAxis.setMaterial(material);
        zAxis.setMaterial(material);

        axisGroup.setVisible(false);
        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        world.getChildren().addAll(axisGroup);
    }

    private VBox addGuiElements() {

        MenuBar menuBar = new MenuBar();
        Menu methods = new Menu("Methods");

        MenuItem tetrahedron = new MenuItem("Tetrahedron");
        MenuItem octahedron = new MenuItem("Octahedron");
        MenuItem icosahedron = new MenuItem("Icosahedron");


        tetrahedron.setOnAction(t -> {
            world.getChildren().remove(pyramidGroup);
            pyramidGroup.getChildren().remove(sphere);
            pyramidGroup.getChildren().remove(sphereMeshView);
            buildPyramid(0);
        });

        octahedron.setOnAction(t -> {
            world.getChildren().remove(pyramidGroup);
            pyramidGroup.getChildren().remove(sphere);
            pyramidGroup.getChildren().remove(sphereMeshView);
            buildPyramid(1);
        });

        icosahedron.setOnAction(t -> {
            world.getChildren().remove(pyramidGroup);
            pyramidGroup.getChildren().remove(sphere);
            pyramidGroup.getChildren().remove(sphereMeshView);
            buildPyramid(2);
        });

        Menu color = new Menu("Color");
        MenuItem fill = new MenuItem("Fill");
        MenuItem line = new MenuItem("Line");

        fill.setOnAction(t -> {
            sphereMeshView.setDrawMode(DrawMode.FILL);
            sphereMeshView.setCullFace(CullFace.BACK);
            PhongMaterial material = new PhongMaterial(Color.BLUEVIOLET);
            sphereMeshView.setMaterial(material);
        });

        line.setOnAction(t -> {
            sphereMeshView.setDrawMode(DrawMode.LINE);
            sphereMeshView.setCullFace(CullFace.BACK);
            PhongMaterial material = new PhongMaterial(Color.BLUEVIOLET);
            sphereMeshView.setMaterial(material);
        });

        Menu additional = new Menu("Additional");
        MenuItem addSphere = new MenuItem("Add/remove sphere");
        MenuItem addAxis = new MenuItem("Add/remove axis");

        addSphere.setOnAction(t -> sphereGroup.setVisible(!sphereGroup.isVisible()));

        addAxis.setOnAction(t -> axisGroup.setVisible(!axisGroup.isVisible()));

        methods.getItems().addAll(tetrahedron, octahedron, icosahedron);
        color.getItems().addAll(fill, line);
        additional.getItems().addAll(addSphere, addAxis);

        menuBar.getMenus().addAll(methods, color, additional);

        return new VBox(menuBar);
    }

    private SubScene createScene3D() {
        SubScene scene3d = new SubScene(world, sceneWidth, sceneHeight, true, SceneAntialiasing.DISABLED);
        scene3d.setFill(Color.LIGHTGRAY);
        camera.setFarClip(100000);
        camera.getTransforms().addAll(
                new Rotate(0, Rotate.Y_AXIS),
                new Rotate(0, Rotate.X_AXIS),
                new Translate(0, 0, -350));
        scene3d.setCamera(camera);

        return scene3d;
    }

    private void buildSphere() {
        Sphere sphere = new Sphere();
        sphere.setRadius(50);

        final PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(Color.BLUE);
        material.setDiffuseColor(Color.DARKBLUE);

        sphere.setMaterial(material);

        sphereGroup.getChildren().addAll(sphere);
        sphereGroup.setVisible(false);
        world.getChildren().addAll(sphereGroup);
    }

    private void handleMouse(Scene scene, Stage stage) {

        Rotate xRotate, yRotate;
        world.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.Y_AXIS),
                yRotate = new Rotate(0, Rotate.X_AXIS));
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(me -> {
            anchorX = me.getSceneX();
            anchorY = me.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(me -> {
            if (me.isPrimaryButtonDown()) {
                angleX.set(anchorAngleX + anchorX - me.getSceneX());
                angleY.set(anchorAngleY - anchorY + me.getSceneY());
            } else if (me.isSecondaryButtonDown()) {
                double delta = anchorX - me.getSceneX();
                double a = world.getTranslateZ() + delta * 0.1;
                if (a < 100 && a > -250)
                    world.translateZProperty().set(a);
            }
        });

        stage.addEventFilter(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            double currentDistance = world.getTranslateZ();
            double newCameraDistance = currentDistance - delta;
            if (newCameraDistance > currentDistance) {
                if (sphere.iteration > 0) {
                    sphere.previousIteration();
                }
            } else if (sphere.iteration < 7) {
                sphere.nextIteration();
            }
            sphereMeshView.setMesh(sphere.mesh);
            triangles.setText("Triangles:    " + sphere.triangles.length);
            volume.setText("    Difference between volumes:    " + (4.0 / 3.0 * Math.PI * 50 * 50 * 50 - sphere.VolumeOfMesh()));
            iteration.setText("       Iteration:    " + (sphere.iteration + 1));
        });
    }

    private void buildPyramid(int number) {
        switch (number) {
            case 0:
                sphere = new Tetrahedron();
                break;
            case 1:
                sphere = new Octahedron();
                break;
            default:
                sphere = new Icosahedron();
                break;
        }
        DrawMode mode = DrawMode.LINE;
        if (sphereMeshView != null) {
            mode = sphereMeshView.getDrawMode();
        }
        sphereMeshView = new MeshView(sphere.mesh);

        triangles.setText("Triangles:    " + sphere.triangles.length);
        volume.setText("    Difference between volumes:    " + (4.0 / 3.0 * Math.PI * 50 * 50 * 50 - sphere.VolumeOfMesh()));
        iteration.setText("       Iteration:    " + (sphere.iteration + 1));

        sphereMeshView.setDrawMode(mode);
        sphereMeshView.setCullFace(CullFace.BACK);
        PhongMaterial material = new PhongMaterial(Color.BLUEVIOLET);
        sphereMeshView.setMaterial(material);

        pyramidGroup.getChildren().addAll(sphereMeshView);

        world.getChildren().addAll(pyramidGroup);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox guiGroup = addGuiElements();

        SubScene subscene = createScene3D();

        HBox statistics = new HBox(iteration, triangles, volume);
        statistics.setSpacing(20);
        statistics.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox layout = new VBox(guiGroup, statistics, subscene);
        subscene.heightProperty().bind(layout.heightProperty());
        subscene.widthProperty().bind(layout.widthProperty());
        layout.setSpacing(0.0);
        Scene scene = new Scene(layout, sceneWidth, sceneHeight, true);

        primaryStage.setScene(scene);
        primaryStage.show();

        handleMouse(scene, primaryStage);

        buildPyramid(0);

        buildAxes();

        buildSphere();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
