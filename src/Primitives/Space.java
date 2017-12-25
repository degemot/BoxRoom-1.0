package Primitives;

import utils.*;

public class Space extends FirstShape {

    public Vec3 pos;
    public float size;
    public FileObj model;
    public float speedAngle;
    Part part;

    private ObjectQueue queue;
    private int currentTime = (int) System.currentTimeMillis();

    public Space(int count, float size, FileObj model){
        queue= new ObjectQueue();
        this.pos= new Vec3();
        this.posAbsol= new Vec3();
        this.size= size;
        this.model= model;
        this.speedAngle= 5;
        ObjectQueue.Queue.add(this);
    }

    @Override
    public void update() {
        if (currentTime + 40 < (int) System.currentTimeMillis()) {
            currentTime += 40;
            addPart();
            addPart();
        }

        queue.toHead();
        while (queue.object != null) {
            queue.object.figure.size = size * (1f - 2 * Math.abs(0.5f - (float) queue.object.figure.lifeTime / queue.object.figure.lifeMax));
            queue.object.figure.posAbsol= posAbsol;
            queue.object.figure.update();
            if (queue.object.figure.lifeMax <= queue.object.figure.lifeTime) {
                queue.delete();
            }
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
        part.angle= new Vec3((float)Math.random()*speedAngle);
        part.speedAngle= new Vec3((float)Math.random()*speedAngle);
        part.pos= new Vec3((float)Math.random()*40-20+pos.x,(float)Math.random()*40-20+pos.y,(float)Math.random()*40-20+pos.z);
        part.speed = new Vec3((float)(Math.random()-0.5)/100,(float)(Math.random()-0.5)/100,(float)(Math.random()-0.5)/100);
        part.size = 0.001f;
        part.lifeMax=200+(int)((float)Math.random()*50);
        queue.add(part);
    }

    class Part extends FirstShape {
        public Part(FileObj model, ShaderProgram program, String texture){
            this.model= model;
            this.program= program;
            this.imagePath= texture;
            this.pos=  new Vec3(0f,0f,0f);
            this.posAbsol= new Vec3();
            this.speed=  new Vec3(0f,0f,0f);
            this.acceleration=  new Vec3(0f,0f,0f);
            this.angle=  new Vec3(0f,0f,0f);
            this.speedAngle= new Vec3(0f,0f,0f);
            this.color= new Vec3(0.3f,0.3f,0.3f);
            this.size= 1f;
        }
    }
}
