public class NBody {

	public static double readRadius(String path) {
		In inClass = new In(path);
		/*
		inClass.readLine();
		double radius = Double.parseDouble(inClass.readLine()); //https://stackoverflow.com/questions/5769669/convert-string-to-double-in-java
		return radius;
		*/
		inClass.readDouble();
		return inClass.readDouble();
	}

	public static Planet[] readPlanets(String path) {
		In inClass = new In(path);
		Double size = inClass.readDouble();
		Planet[] planets;
		planets = new Planet[size.intValue()];      //https://stackoverflow.com/questions/5404149/how-to-convert-double-to-int-directly
		inClass.readLine();
		inClass.readLine();
		
		for (int i = 0; i < size; i++) {
			planets[i] = new Planet(inClass.readDouble(), inClass.readDouble(), inClass.readDouble(), inClass.readDouble(), inClass.readDouble(), inClass.readString());
		}
		return planets;
	}

	public static void main(String[] args) {
		double time = 0;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		int index = 0;

		In inClass = new In(filename);
		int size = inClass.readInt();
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);
		//StdDraw.show(5000);
		while (time < T) {   //https://piazza.com/class/iiklg7j9ggf2vl?cid=448
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i = 0; i < size; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for (int i = 0; i < size; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet p : planets) {
				p.draw();
			}

			StdDraw.show(10);
			time += dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}		
	}
}

