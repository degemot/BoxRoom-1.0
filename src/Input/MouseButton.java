package Input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButton extends GLFWMouseButtonCallback {
    public boolean left, right;
    @Override
    public void invoke(long window, int button, int action, int mods) {
        if ((button == 0) && (action == 1)) {
            left=true;
        }
        if ((button == 0) && (action == 0)) {
            left=false;
        }
        if ((button == 1) && (action == 1)) {
            right=true;
        }
        if ((button == 1) && (action == 0)) {
            right=false;
        }
    }
}