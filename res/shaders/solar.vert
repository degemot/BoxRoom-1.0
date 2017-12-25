#version 330 core


layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec3 normalVector;

uniform mat4 cameraModel;
uniform mat4 cameraProjection;
uniform mat4 cameraView;
uniform vec3 cameraPos;

out vec3 vPosition;
out vec3 vNormal;

void main()
{
    vPosition= vertexPosition;
    vNormal= normalVector;
    gl_Position = cameraView*cameraProjection*cameraModel*vec4(vPosition, 1.0);
}