public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String fileName) {
    
        In in = new In(fileName);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] p = new Planet[N];
            
        for (int i = 0; i < N; i++) {
            p[i] = new Planet(in.readDouble(), in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }   

        return p;
    }   
       
    public static void playPlants(double T, double dt, double R, Planet[] planets) {
        int l = planets.length;
        double t = 0;
        double[] xForces = new double[l];
        double[] yForces = new double[l];
        
        while (t < T) {
            StdDraw.setScale(-R, R);
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (int i = 0; i < l; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);

            }

            for (int i = 0; i < l; i++) {
                planets[i] = planets[i].update(dt, xForces[i], yForces[i]);
            } 
            
            for (int i = 0; i < l; i++) {
                planets[i].draw();
            } 
            
            StdDraw.show(10);

            t += dt;
        }
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        playPlants(T, dt, R, planets);
        
    }
}
