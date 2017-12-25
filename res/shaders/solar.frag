#version 330 core

uniform mat4 cameraModel;
uniform mat4 cameraProjection;
uniform mat4 cameraView;
uniform vec3 color;
uniform sampler2D tex;

in vec3 position;
in vec3 normal;
out vec4 fragColor;

void main()
{
    vec3 normal2= vec3(cameraModel*vec4(position,1)-cameraModel*vec4(0,0,0,1)).xyz;
    normal2= normalize(normal2);

    vec3 seeDir= (inverse(cameraProjection)*vec4(0,0,1,1)).xyz;
    seeDir= normalize(seeDir);
    float seeLight= max(dot(seeDir,normal2),0.1);

    vec4 lighting= vec4(color,1)*seeLight;

    fragColor = lighting;
}