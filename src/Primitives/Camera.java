package Primitives;

import utils.FileObj;
import utils.Matrix;
import utils.Vec3;

public class Camera {
    public static Matrix cameraView= new Matrix();
    public static Matrix cameraProjection= new Matrix();
    public static Vec3 pos= new Vec3();
    public static Vec3 center= new Vec3();
    public static Vec3 angle= new Vec3();

    public static void factor(float factor){
        cameraProjection.setPerspective(45f,factor,0f,10f,false);
    }

    public static void update(){
        cameraView.setIdentity();
        cameraView.translate(pos.x,pos.y,pos.z);
        cameraView.rotate(angle);
    }
}
