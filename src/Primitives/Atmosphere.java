package Primitives;
import utils.*;

public class Atmosphere extends FirstShape {
    FigureObject parent;
    public Atmosphere(FigureObject parent,FileObj model, ShaderProgram program, String texture){
        this.parent= parent;
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
        planet= true;
        name= "cloud";
        ObjectQueue.QueueTrans.add(this);
    }

    public void mouseDetect(){
        float distance= pos.distFromCursor();
        if(distance<size) {
            focused= true;
        }
        else{
            focused= false;
        }

        if(picked){
            //size = parent.size + 0.5f;
        }
        else{
            size= parent.size+0.2f;
        }
    }

    @Override
    public void update(){
        speed.add(acceleration);
        pos.add(speed);
        angle.add(speedAngle);
        cameraModel.setIdentity();
        cameraModel.scale(size);
        cameraModel.rotate(angle);
        cameraModel.translate(pos);
        cameraModel.translate(posAbsol);
        lifeTime++;
        paint();
        mouseDetect();
    }
}
