package com.luisrard.custom.graphics.obj;

public class VertexNormal {
    private float nx, ny, nz;

    public VertexNormal(float nx, float ny, float nz) {
        this.nx = nx;
        this.ny = ny;
        this.nz = nz;
    }

    public float getNx() {
        return nx;
    }

    public void setNx(float nx) {
        this.nx = nx;
    }

    public float getNy() {
        return ny;
    }

    public void setNy(float ny) {
        this.ny = ny;
    }

    public float getNz() {
        return nz;
    }

    public void setNz(float nz) {
        this.nz = nz;
    }

    @Override
    public String toString() {
        return "VertexNormal{" +
                "nx=" + nx +
                ", ny=" + ny +
                ", nz=" + nz +
                '}';
    }
}
