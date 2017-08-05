public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r = Math.sqrt(dy * dy + dx * dx);

        return r;
    }

    public double calcForceExertedBy(Planet p) {
        double r;
        double G = 6.67e-11;

        r = this.calcDistance(p);

        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);

        return F * dx / r;
    }
    
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);

        return F * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double Fx = 0;

        for (Planet element : allPlanets) {
            if (equals(element)) {
                continue;
            } else {
                Fx += calcForceExertedByX(element);
            }
        }
        
        return Fx;
    } 

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double Fy = 0;

        for (Planet element : allPlanets) {
            if (equals(element)) {
                continue;
            } else {
                Fy += calcForceExertedByY(element);
            }
        }

        return Fy;
    }

    public Planet update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;

        return this;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
