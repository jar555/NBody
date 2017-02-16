import static java.lang.Math.sqrt; //https://stackoverflow.com/questions/9898310/java-how-to-specify-absolute-value-and-square-roots

public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67E-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		return (G * this.mass * p.mass)/(calcDistance(p) * calcDistance(p));
	}

	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p) * (p.xxPos)/calcDistance(p) - calcForceExertedBy(p) * (this.xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p) * (p.yyPos)/calcDistance(p) - calcForceExertedBy(p) * (this.yyPos)/calcDistance(p);
	}

	public double calcNetForceExertedByX (Planet[] p) {
		double xTotal = 0;
		for (int i = 0; i < p.length; i ++) {
			if (!p[i].equals(this)) {
				xTotal += calcForceExertedByX(p[i]);
			}
		}
		return xTotal;
	}

	public double calcNetForceExertedByY (Planet[] p) {
		double yTotal = 0;
		for (int i = 0; i < p.length; i ++) {
			if (!p[i].equals(this)) {
				yTotal += calcForceExertedByY(p[i]);
			}
		}
		return yTotal;
	}

	public void update(double time, double xForce, double yForce) {
		double aX = xForce/this.mass;
		double aY = yForce/this.mass;
		this.xxVel += time*aX;
		this.yyVel += time*aY;
		this.xxPos += time*this.xxVel;
		this.yyPos += time*this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}