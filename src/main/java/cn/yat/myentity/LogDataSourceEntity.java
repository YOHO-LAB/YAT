package cn.yat.myentity;

public class LogDataSourceEntity {
    int p;// pass
    int f;// fail
    int s;// skip
    long t;// time total
    long h;// time http
    String l;// log name
    int e; // env id

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public long getH() {
        return h;
    }

    public void setH(long h) {
        this.h = h;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }
}
