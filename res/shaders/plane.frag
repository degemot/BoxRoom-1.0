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

void main()
{
    float radius= sqrt(position.x*position.x+position.z*position.z);
    float alfa;

    if(radius>0.9 && radius<1)
        alfa=1;
    else
        alfa=0;

    fragColor = vec4(color,alfa);
}