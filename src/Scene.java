import Primitives.*;
import utils.Matrix;
import utils.Vec3;

import java.util.Queue;
import java.util.Scanner;

import static utils.FileObj.*;
import static utils.ShaderProgram.shaderBlock;
import static utils.ShaderProgram.shaderPlanet;
import static utils.ShaderProgram.shaderSolar;


public class Scene {
    FigureObject Car;
    FigureObject Plane;


    public Scene(){
        new Space(100,0.1f,modelMicrobe);
        Car= new FigureObject(modelCar,shaderBlock,"none");
        Car.color.set(0f,1f,0.5f);
        Car.size= 0.5f;

        Plane= new FigureObject(modelPlane,shaderBlock,"none");
        Plane.color.set(1f);
        Plane.size= 3f;
    }

    public void action(){
    }

    public void loopRender(){
    }
}