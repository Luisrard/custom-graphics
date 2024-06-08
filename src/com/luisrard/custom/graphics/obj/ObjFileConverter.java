package com.luisrard.custom.graphics.obj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ObjFileConverter {

    public static ObjModel convert(String filePath) throws IOException {
        ObjModel objModel = new ObjModel();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length == 0) {
                    continue;
                }
                switch (tokens[0]) {
                    case "v":
                        objModel.getVertices().add(new Vertex(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2]),
                                Float.parseFloat(tokens[3])
                        ));
                        break;
                    case "vt":
                        objModel.getTextureCoordinates().add(new TextureCoordinate(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2])
                        ));
                        break;
                    case "vn":
                        objModel.getVertexNormals().add(new VertexNormal(
                                Float.parseFloat(tokens[1]),
                                Float.parseFloat(tokens[2]),
                                Float.parseFloat(tokens[3])
                        ));
                        break;
                    case "f":
                        Face face = new Face();
                        for (int i = 1; i < tokens.length; i++) {
                            String[] indices = tokens[i].split("/");
                            face.getVertexIndices().add(Integer.parseInt(indices[0]) - 1);
                            if (indices.length > 1 && !indices[1].isEmpty()) {
                                face.getTextureCoordinateIndices().add(Integer.parseInt(indices[1]) - 1);
                            }
                            if (indices.length > 2 && !indices[2].isEmpty()) {
                                face.getVertexNormalIndices().add(Integer.parseInt(indices[2]) - 1);
                            }
                        }
                        objModel.getFaces().add(face);
                        break;
                    default:
                        // Ignore other lines
                        break;
                }
            }
        }
        return objModel;
    }
}
