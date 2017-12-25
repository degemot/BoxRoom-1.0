#version 330 core

uniform mat4 cameraModel;
uniform mat4 cameraProjection;
uniform mat4 cameraView;
uniform vec3 cameraPos;
uniform sampler2D tex;

uniform vec3 color;

in vec3 position;
in vec3 normal;
in vec3 fragPos;
out vec4 fragColor;

vec3 seeDir;
float seeLight;

void main(){
    vec3 normal2= vec3(cameraModel*vec4(position,1)-cameraModel*vec4(0,0,0,1)).xyz;
    normal2= normalize(normal2);

    seeDir=  cameraPos-vec3(cameraModel*vec4(0,0,0,1));
    seeDir= normalize(seeDir);
    seeLight= max(dot(seeDir,normal2),0.1);
    fragColor = vec4(color, (seeLight-0.24)/2);
}