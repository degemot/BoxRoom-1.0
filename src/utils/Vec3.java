package utils;

import Primitives.Camera;
import Primitives.FigureObject;

public class Vec3 {
    public float x,y,z;

    public Vec3(){
        x=0;y=0;z=0;
    }

    public Vec3(float x,float y,float z){
        this.x=x;this.y=y;this.z=z;
    }

    public Vec3(float x){
        this.x=x;this.y=x;this.z=x;
    }

    public void set(float x, float y, float z){
        this.x=x;this.y=y;this.z=z;
    }

    public void set(float x){
        this.x=x;this.y=x;this.z=x;
    }

    public void set(Vec3 b){
        this.x=b.x;this.y=b.y;this.z=b.z;
    }

    public void add(Vec3 b){
        x+= b.x;
        y+= b.y;
        z+= b.z;
    }

    public void add(float x, float y, float z){
        this.x+= x;
        this.y+= y;
        this.z+= z;
    }

    public void sub(Vec3 b){
        x-= b.x;
        y-= b.y;
        z-= b.z;
    }

    public Vec3 subR(Vec3 b){
        return new Vec3(x-b.x,y-b.y,z-b.z);
    }

    public Vec3 subR(float x,float y,float z){
        return new Vec3(this.x-x,this.y-y,this.z-z);
    }

    public void sub(float x, float y, float z){
        this.x-= x;
        this.y-= y;
        this.z-= z;
    }

    public void multiply(Vec3 b){
        x*= b.x;
        y*= b.y;
        z*= b.z;
    }

    public void multiply(float b){
        x*= b;
        y*= b;
        z*= b;
    }

    public void div(float b){
        x/= b;
        y/= b;
        z/= b;
    }

    public Vec3 divR(float b){
        x/= b;
        y/= b;
        z/= b;
        return new Vec3(x/b,y/b,z/b);
    }

    public float size(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }

    public void normalize(){
        float factor= x*x+y*y+z*z;
        x/=factor;
        y/=factor;
        z/=factor;
    }

    public Vec3 normalizeR(){
        float factor= x*x+y*y+z*z;
        return new Vec3(x/factor,y/factor,z/factor);
    }

    public void invert(){
        x=-x;
        y=-y;
        z=-z;
    }

    public Vec3 invertR(){
        return new Vec3(-x,-y,-z);
    }

    public void sout(){
        System.out.println(x+" "+y+" "+z);
    }

    public float distFromCamera(){
        Vec3 dist= this.subR(-Camera.pos.x,Camera.pos.y,-Camera.pos.z);
        //dist.sout();
        return dist.invertR().size();
    }

    public float distFromCursor(){
        Vec3 point= this;
        Vec3 pointVec= new Vec3();
        Vec3 directVec= new Vec3();

        pointVec.set(Camera.cameraView.invertR().multiplyR(new Vec3(0,0,1),1f));

        directVec.set(FigureObject.mouseVec);
        directVec.sub(pointVec);

        Vec3 mxm= pointVec.subR(point);
        Vec3 mxmS= new Vec3(mxm.y*directVec.z-mxm.z*directVec.y,-(mxm.x*directVec.z-mxm.z*directVec.x),mxm.x*directVec.y-mxm.y*directVec.x);

        return mxmS.size()/directVec.size();
    }
}