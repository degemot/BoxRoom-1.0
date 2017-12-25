#version 330 core

layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec3 normalVector;

uniform mat4 cameraModel;
uniform mat4 cameraProjection;
uniform mat4 cameraView;

out vec3 position;
out vec3 normal;
out vec3 fragPos;

void main()
{
    position= vertexPosition;
    normal= vec3(transpose(inverse(cameraModel))*vec4(normalVector,0)).xyz;
    normal= normalize(normal);
    fragPos= vec3(cameraModel*vec4(position, 1.0));
    gl_Position = cameraView*cameraProjection*cameraModel*vec4(position, 1.0);
}