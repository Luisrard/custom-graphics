package com.luisrard.custom.graphics.obj;

import java.util.ArrayList;
import java.util.List;

public class ObjModel {
    private List<Vertex> vertices = new ArrayList<>();
    private List<TextureCoordinate> textureCoordinates = new ArrayList<>();
    private List<VertexNormal> vertexNormals = new ArrayList<>();
    private List<Face> faces = new ArrayList<>();

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<TextureCoordinate> getTextureCoordinates() {
        return textureCoordinates;
    }

    public void setTextureCoordinates(List<TextureCoordinate> textureCoordinates) {
        this.textureCoordinates = textureCoordinates;
    }

    public List<VertexNormal> getVertexNormals() {
        return vertexNormals;
    }

    public void setVertexNormals(List<VertexNormal> vertexNormals) {
        this.vertexNormals = vertexNormals;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "ObjModel{" +
                "vertices=" + vertices +
                ", textureCoordinates=" + textureCoordinates +
                ", vertexNormals=" + vertexNormals +
                ", faces=" + faces +
                '}';
    }
}
