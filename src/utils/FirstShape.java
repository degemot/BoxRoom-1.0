package utils;
import Primitives.Camera;
import org.lwjgl.opengl.GL20;
import utils.*;

import static org.lwjgl.opengl.GL20.*;

public class FirstShape {
    public boolean picked= false;
    public boolean focused= false;
    public boolean planet= false;
    public String name= new String();

    public Vec3 pos= new Vec3();
    public Vec3 posAbsol= new Vec3();
    public Vec3 speed= new Vec3();
    public Vec3 acceleration= new Vec3();
    public Vec3 angle= new Vec3();
    public Vec3 speedAngle= new Vec3();
    public Vec3 color= new Vec3();
    public float size;
    public int lifeTime= 0;
    public int lifeMax= 10000000;

    public FileObj model;
    public ShaderProgram program;
    public String imagePath;

    public Matrix cameraModel;
    private Vertex meth;
    private Texture texture;
    private int texting=0;

    public FirstShape() {
    }

    public void bind(){
        cameraModel= new Matrix();

        String texturePath= "res/texture/"+imagePath;

        meth= new Vertex(program.Id,model);

        if(imagePath!="none"){
            texting=1;
            texture = new Texture(texturePath);
            glUniform1i(glGetUniformLocation(program.Id,"tex"),0);
        }
    }

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
    }

    public void paint(){
        glUseProgram(program.Id);
        if(texting==1)
        texture.bind();
        glUniform3f(glGetUniformLocation(program.Id,"color"),color.x,color.y,color.z);
        glUniformMatrix4fv(glGetUniformLocation(program.Id, "cameraModel"),false,cameraModel.matrix);
        GL20.glUniformMatrix4fv(glGetUniformLocation(program.Id, "cameraProjection"),false, Camera.cameraView.matrix);
        glUniformMatrix4fv(glGetUniformLocation(program.Id, "cameraView"),false,Camera.cameraProjection.matrix);
        glUniform3f(glGetUniformLocation(program.Id, "cameraPos"),-Camera.pos.x,-Camera.pos.y,-Camera.pos.z);
        meth.render();
        if(texting==1)
        texture.unbind();
    }
}