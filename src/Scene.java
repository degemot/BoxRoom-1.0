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
    FigureObject Car,Box,Sphere,Plane2,Star,Char,Table,Toilet,Syntheizer;
    FigureObject Plane;


    public Scene(){
        Car= new FigureObject(modelCar,shaderBlock,"none",true);
        Car.color.set(0f,1f,0.5f);
        Car.size= 0.5f;
        Car.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Box= new FigureObject(modelBox,shaderBlock,"none",true);
        Box.color.set(1f,0f,0f);
        Box.size= 0.5f;
        Box.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Sphere= new FigureObject(modelSphere,shaderPlanet,"none",true);
        Sphere.color.set(1f,1f,0.5f);
        Sphere.size= 0.5f;
        Sphere.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Star= new FigureObject(modelStar,shaderBlock,"none",true);
        Star.color.set(0.5f,1f,0.5f);
        Star.size= 0.5f;
        Star.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Plane2= new FigureObject(modelPlane,shaderBlock,"none",true);
        Plane2.color.set(1f,0.5f,0.5f);
        Plane2.size= 0.5f;
        Plane2.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Char= new FigureObject(modelChar,shaderBlock,"none",true);
        Char.color.set(1f,1f,1f);
        Char.size= 0.5f;
        Char.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Toilet= new FigureObject(modelToilet,shaderBlock,"none",true);
        Toilet.color.set(1f,1f,1f);
        Toilet.size= 0.5f;
        Toilet.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Table= new FigureObject(modelTable,shaderBlock,"none",true);
        Table.color.set(1f,1f,1f);
        Table.size= 0.5f;
        Table.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);

        Syntheizer= new FigureObject(modelSynthesizer,shaderBlock,"none",true);
        Syntheizer.color.set(1f,1f,1f);
        Syntheizer.size= 0.5f;
        Syntheizer.pos.set((float)Math.random()*2,0.5f,(float)Math.random()*2);



        Plane= new FigureObject(modelPlane,shaderBlock,"none",false);
        Plane.color.set(1f);
        Plane.size= 3f;
    }

    public void action(){
    }

    public void loopRender(){
    }
}