public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /*  initialize an identical Planet object, copy all data */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        return Planet.G * this.mass * p.mass / (r*r);
    }

    /* 无需关心正负号，坐标系做差已经是 向量了
    * 注意事项 exert by， 所以是p在前，减去 this*/
    public double calcForceExertedByX(Planet p){
        double r = this.calcDistance(p);
        return calcForceExertedBy(p) * (p.xxPos - xxPos)/ r;
    }

    public double calcForceExertedByY(Planet p){
        double r = this.calcDistance(p);
        return calcForceExertedBy(p) * (p.yyPos - yyPos)/ r;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double netForce = 0;
        for(Planet p: planets){
            if(!this.equals(p)) netForce += this.calcForceExertedByX(p);
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double netForce = 0;
        for(Planet p: planets){
            if(!this.equals(p)) netForce += this.calcForceExertedByY(p);
        }
        return netForce;
    }

    public void update(double dt, double xForce, double yForce){
        double accX = xForce/mass;
        double accY = yForce/mass;

        xxVel += accX*dt;
        yyVel += accY*dt;

        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    /** draw the picture on the background */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
        StdDraw.show();
    }


}
