package Primitives;
import utils.*;

public class Atmosphere extends FirstShape {
    public Atmosphere(FileObj model, ShaderProgram program, String texture){
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
        ObjectQueue.QueueTrans.add(this);
    }
}
