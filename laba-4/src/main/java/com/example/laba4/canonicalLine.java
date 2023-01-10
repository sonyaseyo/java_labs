package com.example.laba4;

public class canonicalLine {
    public double x;
    public double y;
    public double z;
    public double l;
    public double m;
    public double n;
    public boolean valid;

    public enum relation {
        Parallel,
        Fleeting,
        Perpendicular,
        Copl_intersect,
        Error
    }
    
    public canonicalLine(    double x,
                             double y,
                             double z,
                             double l,
                             double m,
                             double n) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.l = l;
        this.m = m;
        this.n = n;
        valid = l != 0 && m != 0 && n != 0;
    }
    
    public canonicalLine(String x,
                         String y,
                         String z,
                         String l,
                         String m,
                         String n) {
        this(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z),
                Double.parseDouble(l), Double.parseDouble(m), Double.parseDouble(n));
    }

    private boolean compare_float(double a, double b) {
        double e = Math.max(Math.abs(a), Math.abs(b)) / 1000000;
        if (Math.abs(a - b) <= e) return true;
        return false;
    }

    private double determinant(double x1, double y1, double z1,
                               double l1, double m1, double n1,
                               double x2, double y2, double z2,
                               double l2, double m2, double n2) {
        return (x2 - x1)*m1*n2 + (y2 - y1)*n1*l2 + l1*m2*(z2 - z1) - l2*m1*(z2 - z1) - l1*(y2 - y1)*n2 - (x2 - x1)*n1*m2;
    }

    public relation check(canonicalLine line){
        if (!this.valid || !line.valid) return relation.Error;
        double det = determinant( this.x,  this.y,  this.z,
                this.l,  this.m,  this.n,
                line.x,  line.y,  line.z,
                line.l,  line.m,  line.n);

        if (compare_float(det, 0)) {
            if (compare_float(this.l*line.l + this.m*line.m + this.n*line.n, 0)) return relation.Perpendicular;
            if (compare_float(this.l/line.l, this.m/line.m) && compare_float(this.m/line.m, this.n/line.n)) return relation.Parallel;
            if (!compare_float(this.l / line.l, this.m / line.m) ||
                    !compare_float(this.m / line.m, this.n / line.n)) {
                return relation.Copl_intersect;
            }
        };


        return relation.Fleeting;
    }

}
