package org.example;

public class FourierTransform {
    private int[] mapT;
    private int lengthArr;

    public FourierTransform(int length) {
        int l = 1;
        while (l < length) {
            l = l*2;
        }
        mapT = invers(l);
        lengthArr = l;
    }

    public int getLengthArr() {
        return lengthArr;
    }

    private int[] invers(int l) {
        int[] mapt = new int[l];
        for (int i = 0; i < l; i++) {
            int p = i;
            int nn = 0;
            int s = l;
            while (p > 0) {
                s = s/2;
                if (p%2 == 1) {
                    nn = nn + s;
                }
                p = p/2;
            }
            mapt[i] = nn;
        }
        return mapt;
    }

    public Complex[] FFT(Complex[] input) {
        int l = lengthArr;
        int ml = input.length;
        Complex[] buf = new Complex[l];
        for (int a = 0; a < l; a++) {
            int p = mapT[a];
            if (a < ml) {
                buf[p] = input[a];
            } else {
                buf[p] = new Complex(0, 0);
            }
            a++;
            p = mapT[a];
            if (a < ml) {
                buf[p] = input[a].negation();
            } else {
                buf[p] = new Complex(0, 0);
            }
        }

        int m = 2;
        int c = 1;

        while (l > 1) {
            l = l / 2;
            for (int d = 0; d < c; d++) {
                Complex e = Complex.expIm(-(2 * d * Math.PI) / m);
                for (int i3 = 0; i3 < l; i3++) {
                    int n1 = i3 * m + d;
                    int n2 = n1 + c;
                    Complex s0 = buf[n1];
                    Complex s1 = Complex.comMul(buf[n2], e);
                    buf[n2] = Complex.comSub(s0, s1);
                    buf[n1] = Complex.comAdd(s0, s1);
                }
            }
            c = m;
            m = m * 2;
        }
        return buf;
    }

    public Complex[] IFFT (Complex[] input) {
        int l = lengthArr;
        int ml = input.length;
        int u = l;
        Complex[] buf = new Complex[l];
        for (int a = 0; a < l; a++) {
            int p = mapT[a];
            if (a < ml) {
                buf[p] = input[a];
            } else {
                buf[p] = new Complex(0, 0);
            }
        }

        int m = 2;
        int c = 1;

        while (l > 1) {
            l = l / 2;
            for (int d = 0; d < c; d++) {
                Complex e = Complex.expIm((2 * d * Math.PI) / m);
                for (int i3 = 0; i3 < l; i3++) {
                    int n1 = i3 * m + d;
                    int n2 = n1 + c;
                    Complex s0 = buf[n1];
                    Complex s1 = Complex.comMul(buf[n2], e);
                    buf[n2] = Complex.comSub(s0, s1);
                    buf[n1] = Complex.comAdd(s0, s1);
                }
            }
            c = m;
            m = m * 2;
        }

        for (int nr = 0; nr < u; nr++) {
            buf[nr] = Complex.comDiv(buf[nr], u);
            nr++;
            buf[nr] = Complex.comDiv(buf[nr], u).negation();
        }

        return buf;
    }
}
