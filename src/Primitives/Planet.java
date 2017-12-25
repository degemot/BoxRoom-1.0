package Primitives;

import utils.FileObj;
import utils.ObjectQueue;
import utils.ShaderProgram;
import utils.Vec3;

import static utils.FileObj.modelMicrobe;
import static utils.FileObj.modelSphere;
import static utils.ShaderProgram.shaderAtmosphere;


public class Planet extends FigureObject{
    public Vec3 distanse;
    public Vec3 Round;
    public Vec3 center;
    public Vec3 speedRound;
    Atmosphere atmosphere;
    public Particular trace;

    public Planet(FileObj model, ShaderProgram program, String texture, String name, float dis,float size,float angle,float round) {
        super(model,program,texture);
        this.distanse = new Vec3(dis, 0f, 0f);
        this.color = new Vec3((float) Math.random(), (float) Math.random(), (float) Math.random());
        this.speedAngle = new Vec3(0.0f, angle, 0f);
        this.angle= new Vec3(0f,(float)Math.random()*360,0f);
        this.Round= new Vec3(0f,(float)Math.random()*360,(float)Math.random()*20-10);
        this.speedRound = new Vec3(0f, round, 0f);
        this.size = size;
        center= new Vec3();
        this.name= name;
        planet= true;
        ObjectQueue.Queue.add(this);

        atmosphere= new Atmosphere(modelSphere,shaderAtmosphere,"none");
        atmosphere.size= size+0.2f;
        atmosphere.color= color;

        trace= new Particular(1,0.02f,modelMicrobe);
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
            atmosphere.size = size + 0.5f;
        }
        else{
            atmosphere.size= size+0.2f;
        }
    }

    @Override
    public void update(){
        center.add(speed);
        angle.add(speedAngle);
        Round.add(speedRound);
        cameraModel.setIdentity();
        cameraModel.scale(size);
        cameraModel.rotate(angle);
        cameraModel.translate(distanse);
        cameraModel.rotate(Round);
        cameraModel.translate(center);
        pos= cameraModel.multiplyR(new Vec3(0,0,0),1);
        mouseDetect();
        paint();

        if(atmosphere!=null) {
            trace.pos = pos;
            atmosphere.pos = pos;
        }
    }
}