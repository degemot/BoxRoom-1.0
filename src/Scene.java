import Primitives.*;
import utils.FileObj;
import utils.Matrix;
import utils.ShaderProgram;
import utils.Vec3;

import java.util.Queue;
import java.util.Scanner;

import static utils.FileObj.*;
import static utils.ShaderProgram.shaderBlock;
import static utils.ShaderProgram.shaderPlanet;
import static utils.ShaderProgram.shaderSolar;


public class Scene {
    FigureObject Plane;


    public Scene(){
        addObject(modelToilet,shaderBlock);

        addObject(modelStar,shaderBlock);

        addObject(modelSynthesizer,shaderBlock);

        Plane= new FigureObject(modelPlane,shaderBlock,"none",false);
        Plane.color.set(1f);
        Plane.size= 3f;
    }

    public void addObject(FileObj modelCar, ShaderProgram shaderBlock){
        FigureObject obj= new FigureObject(modelCar,shaderBlock,"none",true);
        obj.color.set(0f,1f,1f);
        obj.size= 0.5f;
        obj.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);
    }

    public void action(){
    }

    public void loopRender(){
    }
}