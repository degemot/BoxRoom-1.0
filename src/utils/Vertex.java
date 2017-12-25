package utils;

import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class Vertex {
    private int VAO;
    private int VBO;
    private int EBO;
    private int NBO;
    private FileObj model;

    public Vertex(int program,FileObj model){
        glUseProgram(program);
        this.model= model;

        VAO= glGenVertexArrays();
        glBindVertexArray(VAO);
            //VBO
            VBO= glGenBuffers();
            glBindBuffer(GL15.GL_ARRAY_BUFFER,VBO);
            glBufferData(GL_ARRAY_BUFFER, Buffer.getBuffer(model.vertex),GL_STATIC_DRAW);
            glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);

            //NBO
            NBO= glGenBuffers();
            glBindBuffer(GL15.GL_ARRAY_BUFFER,NBO);
            glBufferData(GL_ARRAY_BUFFER, Buffer.getBuffer(model.normal),GL_STATIC_DRAW);
            glVertexAttribPointer(1,3,GL_FLOAT,false,0,0);

            //EBO
            EBO= glGenBuffers();
            glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,EBO);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER,Buffer.getBuffer(model.index),GL_STATIC_DRAW);
        glBindVertexArray(0);
    }

    public void render(){
        glBindVertexArray(VAO);
        glBindBuffer(GL_ARRAY_BUFFER,VBO);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
            //glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
        glDrawElements(GL_TRIANGLES,model.index.length,GL_UNSIGNED_INT,0);
        //glDrawArrays(GL_TRIANGLES,0,vertex.length);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }
}
