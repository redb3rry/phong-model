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
        ks = 0.8;
        kd = 0.3;
        n = 20;
        hue = 22;
        saturation = (double) 67 / 100;
    }
    public void setPlastic() {
        ks = 0.6;
        kd = 0.4;
        n = 4;
        hue = 0;
        saturation = (double) 100 / 100;
    }
    public void setWood(){
        ks = 0.1;
        kd = 0.8;
        n = 5;
        hue = 21;
        saturation = (double) 50/100;
    }
    public void setMaterial(){
        ks = 0.15;
        kd = 0.85;
        n = 20;
        hue = 180;
        saturation = (double) 16/100;
    }

    public double getZ(double x, double y) {
        return Math.sqrt(diameter - Math.pow((x - this.x), 2) - Math.pow((y - this.y), 2)) + this.z;
    }

    public boolean isInSphere(double x, double y) {
        return Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2) <= diameter;
    }

}
