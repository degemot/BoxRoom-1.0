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
    private Planet Solar,Mercury,Venus,Earth,Moon,Mars,Jupiter,Saturn,Uranus,Neptune,Pluto;
    private final float Dis=1.4f,Size=0.1f;
    private final float solarSize=2f;
    private float Angle= 1f, Round= 1f;


    public Scene(){
        new Space(100,0.1f,modelMicrobe);

        Solar = new Planet(modelSphere, shaderSolar, "none","Solar",
                0,solarSize,0.033f*Angle,0);
        Solar.color.set(1f,0.5f,0.1f);

        Mercury= new Planet(modelSphere, shaderPlanet,"none","Mercury",
                0.386f*Dis+solarSize,0.38f*Size,58*Angle,4.16f*Round);

        Venus= new Planet(modelSphere, shaderPlanet,"none","Venus",
                0.72f*Dis+solarSize,0.93f*Size,116*Angle,1.6f*Round);

        Earth= new Planet(modelSphere, shaderPlanet,"none","Earth",
                1*Dis+solarSize,Size,1*Angle,1*Round);
        Earth.color= new Vec3(0.2f,1f,1f);

        Moon= new Planet(modelSphere, shaderPlanet,"none","Moon",
                0.5f*Dis,0.26f*Size,1*Angle,0.03f*Round);
        Moon.color.set(0f,1f,0f);

        Mars= new Planet(modelSphere, shaderPlanet,"none","Mars",
                1.52f*Dis+solarSize,0.53f*Size,1*Angle,0.526f*Round);
        Mars.speedRound.z= 0;
        Mars.color.set(1f,0.2f,0.2f);

        Jupiter= new Planet(modelSphere, shaderPlanet,"none","Jupiter",
                5.18f*Dis+solarSize,11f*Size,0.4f*Angle,0.08f*Round);
        Jupiter.color.set(1f);

        Saturn= new Planet(modelSphere, shaderPlanet,"none","Saturn",
                9.33f*Dis+solarSize,9f*Size,0.4f*Angle,0.034f*Round);

        Uranus= new Planet(modelSphere, shaderPlanet,"none","Uranus",
                19.13f*Dis+solarSize,3.9f*Size,0.4f*Angle,0.012f*Round);

        Neptune= new Planet(modelSphere, shaderPlanet,"none","Neptune",
                29.9f*Dis+solarSize,3.8f*Size,0.6f*Angle,0.0061f*Round);

        Pluto= new Planet(modelSphere, shaderPlanet,"none","Pluto",
                40f*Dis+solarSize,0.171f*Size,6*Angle,0.004f*Round);
    }

    public void action(){
    }

    public void loopRender(){
        Moon.center= Earth.pos;
    }
}