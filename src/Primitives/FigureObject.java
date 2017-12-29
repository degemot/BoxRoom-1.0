package Primitives;
import utils.*;

import static utils.FileObj.modelSphere;
import static utils.ShaderProgram.shaderAtmosphere;

public class FigureObject extends FirstShape {
    public static Vec3 mouseVec= new Vec3();
    public Atmosphere atmosphere;
    public FigureObject(FileObj model, ShaderProgram program, String texture, Boolean mouse){
        this.model= model;
        this.program= program;
        this.imagePath= texture;
        this.pos=  new Vec3();
        this.posAbsol= new Vec3();
        this.speed=  new Vec3();
        this.acceleration=  new Vec3();
        this.angle=  new Vec3();
        this.speedAngle= new Vec3();
        this.color= new Vec3(0.3f);
        this.size= 1f;
        ObjectQueue.Queue.add(this);


        if(mouse) {
            atmosphere = new Atmosphere(this, modelSphere, shaderAtmosphere, "none");
            atmosphere.size = size + 0.2f;
            atmosphere.color = color;
            atmosphere.pos= pos;
        }
    }
}
