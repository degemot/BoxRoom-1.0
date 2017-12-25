package Input;


import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler extends GLFWCursorPosCallback{
    public double x,y;
    @Override
    public void invoke(long window, double x, double y) {
        this.x= x;
        this.y= y;
    }
}
