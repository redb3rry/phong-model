package phong;

public class Light {
    double x, y, z;
    double intensity;
    double step = 10;

    public Light(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.intensity = 100.0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public void moveUp() {
        this.y -= step;
    }

    public void moveDown() {
        this.y += step;
    }

    public void moveLeft() {
        this.x -= step;
    }

    public void moveRight() {
        this.x += step;
    }

}
