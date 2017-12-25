package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileObj {

    private static String shereS= "res/object/sphere.obj";
    public static FileObj modelSphere= new FileObj(shereS);
    private static String boxS= "res/object/box.obj";
    public static FileObj modelBox= new FileObj(boxS);
    private static String carS= "res/object/car.obj";
    public static FileObj modelCar= new FileObj(carS);
    private static String starS= "res/object/star.obj";
    public static FileObj modelStar= new FileObj(starS);
    private static String star2S= "res/object/star2.obj";
    public static FileObj modelStar2= new FileObj(star2S);
    private static String pewS= "res/object/pew.obj";
    public static FileObj modelPew= new FileObj(pewS);
    private static String microbeS= "res/object/microbe.obj";
    public static FileObj modelMicrobe= new FileObj(microbeS);
    private static String planeS= "res/object/plane.obj";
    public static FileObj modelPlane= new FileObj(planeS);

    public static String loadAsString(String file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public float[] vertex;
    public int[] index;
    public float[] normal;
    public int[] indexFirst;
    public float[] normalFirst;
    public float[] vertexFirst;

    public FileObj(String file) {
        // length
        String[] line;
        String[] indexLine;
        int vertexLength=0, indexLength=0, normalLength=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                if(buffer.startsWith("v "))
                    vertexLength++;
                if(buffer.startsWith("f "))
                    indexLength++;
                if(buffer.startsWith("vn "))
                    normalLength++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        vertexFirst= new float[vertexLength*3];
        indexFirst= new int[indexLength*3];
        normalFirst= new float[indexLength*3];

        vertex= new float[indexLength*9];
        index= new int[indexLength*3];
        normal= new float[indexLength*9];

        int vertexId=0,indexId=0,normalId=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                line= buffer.split(" ");
                if(buffer.startsWith("v ")){
                    vertexFirst[vertexId*3+0]= Float.parseFloat(line[1]);
                    vertexFirst[vertexId*3+1]= Float.parseFloat(line[2]);
                    vertexFirst[vertexId*3+2]= Float.parseFloat(line[3]);
                    vertexId++;
                }
                if(buffer.startsWith("vn ")){
                    normalFirst[normalId*3+0]= Float.parseFloat(line[1]);
                    normalFirst[normalId*3+1]= Float.parseFloat(line[2]);
                    normalFirst[normalId*3+2]= Float.parseFloat(line[3]);
                    normalId++;
                }
                if(buffer.startsWith("f ")){

                    index[indexId*3+0]= indexId*3+0;
                    index[indexId*3+1]= indexId*3+1;
                    index[indexId*3+2]= indexId*3+2;

                    indexLine= line[1].split("/");
                    indexFirst[indexId*3]= Integer.parseInt(indexLine[0])-1;
                    normal[indexId*9+0]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+0];
                    normal[indexId*9+1]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+1];
                    normal[indexId*9+2]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+2];

                    vertex[indexId*9+0]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+0];
                    vertex[indexId*9+1]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+1];
                    vertex[indexId*9+2]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+2];


                    indexLine= line[2].split("/");
                    indexFirst[indexId*3+1]= Integer.parseInt(indexLine[0])-1;
                    normal[indexId*9+3]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+0];
                    normal[indexId*9+4]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+1];
                    normal[indexId*9+5]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+2];

                    vertex[indexId*9+3]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+0];
                    vertex[indexId*9+4]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+1];
                    vertex[indexId*9+5]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+2];


                    indexLine= line[3].split("/");
                    indexFirst[indexId*3+2]= Integer.parseInt(indexLine[0])-1;
                    normal[indexId*9+6]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+0];
                    normal[indexId*9+7]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+1];
                    normal[indexId*9+8]= normalFirst[(Integer.parseInt(indexLine[2])-1)*3+2];

                    vertex[indexId*9+6]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+0];
                    vertex[indexId*9+7]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+1];
                    vertex[indexId*9+8]= vertexFirst[(Integer.parseInt(indexLine[0])-1)*3+2];

                    indexId++;
                }
            }
            reader.close();
            indexFirst= null;
            vertexFirst= null;
            normalFirst= null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
