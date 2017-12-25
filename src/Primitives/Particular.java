package Primitives;

import utils.*;

public class Particular extends FirstShape {

    public Vec3 pos;
    public Vec3 posAbsol;
    public float size;
    public FileObj model;
    public float speedAngle;
    int count;
    Part part;

    private ObjectQueue queue;
    private int currentTime = (int) System.currentTimeMillis();

    public Particular(int count, float size, FileObj model){
        queue= new ObjectQueue();
        pos= new Vec3(0f,0f,0f);
        posAbsol= new Vec3(0f,0f,0f);
        this.size= size;
        this.model= model;
        this.speedAngle= 5;
        this.count= count;
        ObjectQueue.Queue.add(this);
    }

    @Override
    public void update(){
        if (currentTime + 20 < (int) System.currentTimeMillis()) {
            currentTime += 20;
            for (int i=0;i<count;i++)
                addPart();
        }

        queue.toHead();
        if(queue.object!=null)
        while(queue.object!=null) {
            queue.object.figure.size= size*(1-(float)queue.object.figure.lifeTime/queue.object.figure.lifeMax);
            queue.object.figure.posAbsol= posAbsol;
            queue.object.figure.update();
            if(queue.object.figure.lifeMax<=queue.object.figure.lifeTime)
                queue.delete();
            queue.next();
        }
    }

    @Override
    public void paint(){
    }

    @Override
    public void bind(){
    }

    private void addPart(){
        part = new Part(model, ShaderProgram.shaderBlock, "none");
        part.pos = new Vec3(0f, 0f, 0f);
        part.posAbsol= posAbsol;
        part.color.set(1f);
        part.angle= new Vec3((float)Math.random()*speedAngle);
        part.speedAngle= new Vec3((float)Math.random()*speedAngle);
        part.pos.add(pos);
        part.speed = new Vec3((float)(Math.random()-0.5)/100,(float)(Math.random()-0.5)/100,(float)(Math.random()-0.5)/100);
        part.size = size;
        part.lifeMax=50+(int)((float)Math.random()*50);
        queue.add(part);
    }

    class Part extends FirstShape {
        public Part(FileObj model, ShaderProgram program, String texture){
            this.model= model;
            this.program= program;
            this.imagePath= texture;
        }
    }
}
