package utils;

import java.io.File;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class ShaderProgram {


    private static String solarVertS= "res/shaders/solar.vert";
    private static String solarFragS= "res/shaders/solar.frag";
    private static String solarGeomS= "res/shaders/solar.geom";
    public static ShaderProgram shaderSolar= new ShaderProgram(solarVertS, solarFragS, solarGeomS);

    private static String blockVertS= "res/shaders/block.vert";
    private static String blockFragS= "res/shaders/block.frag";
    private static String blockGeomS= "res/shaders/block.geom";
    public static ShaderProgram shaderBlock= new ShaderProgram(blockVertS, blockFragS, blockGeomS);

    private static String planeVertS= "res/shaders/plane.vert";
    private static String planeFragS= "res/shaders/plane.frag";
    private static String planeGeomS= "res/shaders/plane.geom";
    public static ShaderProgram shaderPlane= new ShaderProgram(planeVertS, planeFragS, planeGeomS);

    private static String atmosphereVertS= "res/shaders/atmosphere.vert";
    private static String atmosphereFragS= "res/shaders/atmosphere.frag";
    private static String atmosphereGeomS= "res/shaders/atmosphere.geom";
    public static ShaderProgram shaderAtmosphere= new ShaderProgram(atmosphereVertS, atmosphereFragS, atmosphereGeomS);

    private static String planetVertS= "res/shaders/planet.vert";
    private static String planetFragS= "res/shaders/planet.frag";
    private static String planetGeomS= "res/shaders/planet.geom";
    public static ShaderProgram shaderPlanet= new ShaderProgram(planetVertS, planetFragS, planetGeomS);


    public int Id;
    public ShaderProgram(String vertFile,String fragFile, String geomFile){
        int programId= glCreateProgram();
        int vertShaderId= glCreateShader(GL_VERTEX_SHADER);
        int fragShaderId= glCreateShader(GL_FRAGMENT_SHADER);
        int geomShaderId= glCreateShader(GL_GEOMETRY_SHADER);
        String vertText= FileObj.loadAsString(vertFile);
        String fragText= FileObj.loadAsString(fragFile);
        glShaderSource(vertShaderId,vertText);
        glShaderSource(fragShaderId,fragText);

        glCompileShader(vertShaderId);
        if(glGetShaderi(vertShaderId,GL_COMPILE_STATUS)== GL_FALSE) {
            System.out.println("Vertex shader compile error...");
            System.out.println(glGetShaderInfoLog(vertShaderId));
        }
        glCompileShader(fragShaderId);
        if(glGetShaderi(fragShaderId,GL_COMPILE_STATUS)== GL_FALSE){
            System.out.println("Fragment shader compile error...");
            System.out.println(glGetShaderInfoLog(fragShaderId));
        }

        //geometr
        File geom= new File(geomFile);
        if(geom.exists()) {
            String geomText = FileObj.loadAsString(geomFile);
            glShaderSource(geomShaderId,geomText);
            glCompileShader(geomShaderId);
            if(glGetShaderi(geomShaderId,GL_COMPILE_STATUS)== GL_FALSE){
                System.out.println("Geometry shader compile error...");
                System.out.println(glGetShaderInfoLog(geomShaderId));
            }
            glAttachShader(programId,geomShaderId);
        }

        glAttachShader(programId,vertShaderId);
        glAttachShader(programId,fragShaderId);
        glLinkProgram(programId);
        glValidateProgram(programId);

        glDeleteShader(vertShaderId);
        glDeleteShader(fragShaderId);
        if(geom.exists()) {
            glDeleteShader(geomShaderId);
        }


        Id= programId;
    }
}
