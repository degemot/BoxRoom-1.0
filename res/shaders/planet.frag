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


vec3 lightDir;
vec3 lighting;
vec3 lightPos;
vec3 seeDir;

float diffuse;
float specular;
float seeLight;

void main()
{
    vec3 normal2= vec3(cameraModel*vec4(position,1)-cameraModel*vec4(0,0,0,1)).xyz;
    normal2= normalize(normal2);

    lightPos= vec3(0,0,0);
    lightDir= lightPos-(cameraModel*vec4(position,1.0)).xyz;
    lightDir= normalize(lightDir);
    diffuse= max(dot(lightDir,normal2),0.1);


    seeDir=  cameraPos-fragPos;
    seeDir= normalize(seeDir);
    specular= pow(max(dot(vec3(1,0,0),reflect(-lightDir,normal2)),0),1);

    seeLight= max(dot(seeDir,normal2),0.1)*0.2;

    float distance= sqrt(lightDir.x*lightDir.x+lightDir.y*lightDir.y+lightDir.z*lightDir.z);
    lighting= (color+vec3(texture(tex,position.xy)).xyz)*(seeLight+diffuse);
    fragColor = vec4(lighting, 1.0);
}