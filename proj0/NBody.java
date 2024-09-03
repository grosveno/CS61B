public class NBody {
    public static double readRadius(String text) {
        In in = new In(text);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String text) {
        In in = new In(text);
        int N = in.readInt();
        Planet[] allPlanets = new Planet[N];
        in.readDouble();
        for (int i = 0; i < N; i++) {
            allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                                       in.readDouble(), in.readDouble(), in.readString());
        }
        return allPlanets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
        for (Planet p : allPlanets) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        double time = 0;
        int n = allPlanets.length;
        double[] xForces = new double[n];
        double[] yForces =new double[n];
        for (int i = 0; i < n; i++) {
            xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
            yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
        }
        while (time <= T) {
            for (int i = 0; i < n; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
            for (Planet p : allPlanets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                         allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                         allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
    }
}