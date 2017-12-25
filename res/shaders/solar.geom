#version 330 core

layout (triangles) in;
layout (triangle_strip, max_vertices = 6) out;

uniform mat4 cameraProjection;
uniform mat4 cameraView;
uniform mat4 cameraModel;

in vec3 vPosition[];
in vec3 vNormal[];
out vec3 position;
out vec3 normal;

void main ()
{
    position= vPosition[0];
    gl_Position= gl_in[0].gl_Position;
    EmitVertex();
    position= vPosition[1];
    gl_Position= gl_in[1].gl_Position;
    EmitVertex();
    position= vPosition[2];
    gl_Position= gl_in[2].gl_Position;
    EmitVertex();
    EndPrimitive();

    position= vPosition[0];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4(vPosition[0],1);
    EmitVertex();
    position= vPosition[1];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4(vPosition[1],1);
    EmitVertex();
    position= vPosition[2];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4((vPosition[1]+vPosition[2])/2+vNormal[2]/25,1);
    EmitVertex();
    EndPrimitive();

    position= vPosition[0];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4(vPosition[0],1);
    EmitVertex();
    position= vPosition[1];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4((vPosition[1]+vPosition[2])/2+vNormal[1]/25,1);
    EmitVertex();
    position= vPosition[2];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4(vPosition[2],1);
    EmitVertex();
    EndPrimitive();
}

/*
    position= vPosition[0];
    normal= mat3(cameraModel)*vPosition[0];
    gl_Position= cameraView*cameraProjection*cameraModel*vec4(vPosition[0]+vNormal[0]);
    EmitVertex();
    position= vPosition[1];
    normal= mat3(cameraModel)*vPosition[1];
    gl_Position= gl_in[1].gl_Position;
    EmitVertex();
    position= vPosition[2];
    normal= mat3(cameraModel)*vPosition[2];
    gl_Position= gl_in[2].gl_Position;
    EmitVertex();
    EndPrimitive();
    */