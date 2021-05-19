package phong;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    Sphere sphere;
    Light light;
    double Ia, Ka, fatt;
    @FXML
    private Canvas viewport;

    public void initialize() {
        prepareBackground();
        GraphicsContext gc = viewport.getGraphicsContext2D();
        sphere = new Sphere(400, 400, 0, 250);
        light = new Light(400, 400, 500);
        Ia = 80;
        Ka = 0.4;
        fatt = 0.9;
        draw();
    }

    @FXML
    public void readKeys(KeyEvent e) {
        if (light != null) {
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
                case "DIGIT1":
                    sphere.setMetal();
                    break;
                case "DIGIT2":
                    sphere.setWood();
                    break;
                case "DIGIT3":
                    sphere.setPlastic();
                    break;
                case "DIGIT4":
                    sphere.setMaterial();
                    break;
                case "P":
                    light.setIntensity(light.getIntensity()+5);
                    break;
                case "L":
                    light.setIntensity(light.getIntensity()-5);
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

    private ArrayList<Double> normalizeVector(ArrayList<Double> vect) {
        ArrayList<Double> resultVect = new ArrayList<>();
        double sum = 0;
        for (double i : vect
        ) {
            sum += i * i;
        }
        double norm = Math.sqrt(sum);
        resultVect.add(vect.get(0) / norm);
        resultVect.add(vect.get(1) / norm);
        resultVect.add(vect.get(2) / norm);
        return resultVect;
    }

    private ArrayList<Double> subtractVector(double x1, double y1, double z1, double x2, double y2, double z2) {
        ArrayList<Double> resultVect = new ArrayList<>();
        resultVect.add(x2 - x1);
        resultVect.add(y2 - y1);
        resultVect.add(z2 - z1);
        return resultVect;
    }

    private double dotMultiplication(ArrayList<Double> vect1, ArrayList<Double> vect2){
        return vect1.get(0)*vect2.get(0) + vect1.get(1)*vect2.get(1) + vect1.get(2)*vect2.get(2);
    }

    private double getVectorValue(ArrayList<Double> vect){
        return Math.sqrt(Math.pow(vect.get(0),2) + Math.pow(vect.get(1),2) + Math.pow(vect.get(2),2));
    }

    private void draw() {
        prepareBackground();
        PixelWriter pxWriter = viewport.getGraphicsContext2D().getPixelWriter();
        ArrayList<Double> obsVect = new ArrayList<>();
        obsVect.add(0.0); obsVect.add(0.0); obsVect.add(-1.0);

        for (int x = 0; x <= 800; x++) {
            for (int y = 0; y <= 800; y++) {
                if (!sphere.isInSphere(x, y)) {
                    continue;
                } else {
                    double z = sphere.getZ(x, y);
                    ArrayList<Double> normalVector = subtractVector(sphere.x, sphere.y, sphere.z, x, y, z);
                    normalVector = normalizeVector(normalVector);

                    ArrayList<Double> lightVector = subtractVector( x, y, z, light.x, light.y, light.z);
                    lightVector = normalizeVector(lightVector);

                    double dot = dotMultiplication(obsVect, lightVector)/ (getVectorValue(obsVect)*getVectorValue(lightVector));

                    double angle = Math.acos(dot);

                    double backgroundPart = Ia * Ka;
                    double specularPart = sphere.ks * fatt * light.intensity * Math.pow(Math.cos(angle), sphere.n);
                    double diffusedPart  = light.intensity * sphere.kd * fatt * dotMultiplication(normalVector,lightVector);
                    double pointIntensity = (backgroundPart + specularPart + diffusedPart)/100;
                    if(pointIntensity > 1){
                        pointIntensity = 1;
                    } else if(pointIntensity < 0){
                        pointIntensity = 0;
                    }
                    Color color = Color.hsb(sphere.hue, sphere.saturation, pointIntensity);
                    pxWriter.setColor(x,y,color);
                }
            }
        }
    }
}
