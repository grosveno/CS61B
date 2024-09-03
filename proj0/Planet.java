public class Planet {
    private static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
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
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * mass * p.mass / (r * r);
    }
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * dx / r;
    }
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * dy / r;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double f = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) continue;
            f += this.calcForceExertedByX(p);
        }
        return f;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double f = 0;
        for (Planet p : allPlanets) {
            if (this.equals(p)) continue;
            f += this.calcForceExertedByY(p);
        }
        return f;
    }
    public void update(double t, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        this.xxVel += ax * t;
        this.yyVel += ay * t;
        this.xxPos += this.xxVel * t;
        this.yyPos += this.yyVel * t;
    }
    public void draw() {
        String imgName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgName);
    }
}