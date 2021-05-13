package phong;

public class Sphere {
    double x, y, z, radius, diameter;
    double ks, kd, n, hue, saturation;

    public Sphere(double x, double y, double z, double radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.diameter = radius * radius;
        setMetal();
    }

    public void setMetal() {
        ks = 0.92;
        kd = 0.53;
        n = 100;
        hue = 25;
        saturation = (double) 57 * 255 / 100;
    }

    public double getZ(double x, double y) {
        return Math.sqrt(diameter - Math.pow((x - this.x), 2) - Math.pow((y - this.y), 2)) + this.z;
    }

    public boolean isInSphere(double x, double y) {
        return Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2) <= diameter;
    }

}
