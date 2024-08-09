package org.example;

public class Complex {

    private double real, imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double getModule() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }

    public double getArgument() {
        double a = Math.atan2(imaginary, real);
        if (imaginary > 0) {
            return a;
        } else {
            return (a + 2*Math.PI);
        }
    }

    public Complex getConjugate() {
        return new Complex(real, -imaginary);
    }

    public Complex negation() {
        return new Complex(-real, -imaginary);
    }

    public static Complex comAdd(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imaginary + b.imaginary);
    }

    public static Complex comSub(Complex a, Complex b) {
        return new Complex(a.real - b.real, a.imaginary - b.imaginary);
    }

    public static Complex comMul(Complex a, Complex b) {
        double r, i;
        r = a.real * b.real - a.imaginary * b.imaginary;
        i = a.imaginary * b.real + a.real * b.imaginary;
        return new Complex(r, i);

    }
    public static Complex comMul(Complex a, double b) {
        return new Complex(a.real*b, a.imaginary*b);
    }


    public static Complex comDiv(Complex a, Complex b) {
        if (b.real != 0 && b.imaginary != 0) {
            double m2 = b.real * b.real + b.imaginary * b.imaginary;
            double r = (a.real*b.real + a.imaginary*b.imaginary)/m2;
            double i = (a.imaginary*b.real - a.real*b.imaginary)/m2;
            return new Complex(r, i);
        } else {
            return a;
        }
    }

    public static Complex comDiv(Complex a, double b) {
        if (b != 0) {
            return new Complex(a.real/b, a.imaginary/b);
        } else {
            return a;
        }
    }

    public static Complex expIm(double x) {
        return new Complex(Math.cos(x), Math.sin(x));
    }

    public static Complex sqrt(Complex a) {
        double moda = Math.sqrt(a.real*a.real + a.imaginary*a.imaginary);
        double ra;
        double rb;
        if (a.real > 0) {
            ra = Math.sqrt((moda + a.real)/2);
            if (a.imaginary < 0) {
                ra = -ra;
            }
            rb = a.imaginary/(2*ra);
        } else {
            rb = Math.sqrt((moda - a.real)/2);
            ra = a.imaginary/(2*rb);
        }
        return new Complex(ra, rb);
    }
}
