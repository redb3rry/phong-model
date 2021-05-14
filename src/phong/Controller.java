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
    Light light;
    @FXML
    private Canvas viewport;

    public void initialize() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        sphere = new Sphere(400,400,100,250);
        light = new Light(400,400,50);
        draw();
    }

    @FXML
    public void readKeys(KeyEvent e) {
        if(light!=null) {
            String key = e.getCode().toString();
            switch (key) {
                case "A":
                    light.moveLeft();
                    break;
                case "D":
                    light.moveRight();
                    break;
                case "W":
                    light.moveUp();
                    break;
                case "S":
                    light.moveDown();
                    break;
            }
            draw();
        }
    }

    private void prepareBackground() {
        GraphicsContext gc = viewport.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 800);
    }

    private void draw(){
        prepareBackground();
        for (int x = 0; x <= 800; x++){
            for (int y = 0; y <= 800; y++){
                if(!sphere.isInSphere(x,y)){
                    continue;
                } else {
                    double sphereZ = sphere.getZ(x,y);

                }
            }
        }
    }
}
