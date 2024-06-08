package com.luisrard.custom.graphics.obj;

public class TextureCoordinate {
    private float u, v;

    public TextureCoordinate(float u, float v) {
        this.u = u;
        this.v = v;
    }

    public float getU() {
        return u;
    }

    public void setU(float u) {
        this.u = u;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "TextureCoordinate{" +
                "u=" + u +
                ", v=" + v +
                '}';
    }
}