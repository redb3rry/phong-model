package phong;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class Controller {

    Sphere sphere;

    @FXML
    private Canvas viewport;

    public void initialize() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        sphere = new Sphere(400,400,100,250);
    }

    @FXML
    public void readKeys(KeyEvent e) {

    }

    private void prepareBackground() {
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 800);
    }

}
