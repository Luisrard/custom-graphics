package com.luisrard.custom.graphics.obj;

import java.util.ArrayList;
import java.util.List;

public class Face {
    private List<Integer> vertexIndices = new ArrayList<>();
    private List<Integer> textureCoordinateIndices = new ArrayList<>();
    private List<Integer> vertexNormalIndices = new ArrayList<>();

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public void setVertexIndices(List<Integer> vertexIndices) {
        this.vertexIndices = vertexIndices;
    }

    public List<Integer> getTextureCoordinateIndices() {
        return textureCoordinateIndices;
    }

    public void setTextureCoordinateIndices(List<Integer> textureCoordinateIndices) {
        this.textureCoordinateIndices = textureCoordinateIndices;
    }

    public List<Integer> getVertexNormalIndices() {
        return vertexNormalIndices;
    }

    public void setVertexNormalIndices(List<Integer> vertexNormalIndices) {
        this.vertexNormalIndices = vertexNormalIndices;
    }

    @Override
    public String toString() {
        return "Face{" +
                "vertexIndices=" + vertexIndices +
                ", textureCoordinateIndices=" + textureCoordinateIndices +
                ", vertexNormalIndices=" + vertexNormalIndices +
                '}';
    }
}