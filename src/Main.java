
import Input.KeyboardHandler;
import Input.MouseButton;
import Input.MouseHandler;
import Primitives.Camera;
import Primitives.FigureObject;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import utils.FirstShape;
import utils.ObjectQueue;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;


public class Main extends Thread{
    private static int widthScene= 1920, heightScene= 1080;
    private static int widthWindow= 1245, heightWindow= 700;
    private GLFWKeyCallback keyCallback;
    private MouseHandler mouse;
    private MouseButton mouseButton;
    private Scene scene;
    long window;
    FirstShape focused,picked;


    public Main(){
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window= glfwCreateWindow(widthWindow,heightWindow,"NewCreate",0,0);
        glfwSetWindowPos(window,(widthScene-widthWindow)/2,(heightScene-heightWindow)/2);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
        createCapabilities();
        glClearColor(0f, 0f, 0f, 1f);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        scene= new Scene();
        glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
        glfwSetCursorPosCallback(window,mouse= new MouseHandler());
        glfwSetMouseButtonCallback(window,mouseButton= new MouseButton());
        Camera.factor((float)widthWindow/heightWindow);
        Camera.pos.set(0,-1,-3);
        glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
                heightWindow= height;
                widthWindow= width;
                Camera.factor((float)width/height);
            }
        });

        System.out.println("System Solar Engine init.");
    }


    @Override
    public void run(){
        int currentTime = (int) System.currentTimeMillis();
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            if (currentTime + 20 < (int) System.currentTimeMillis()) {
                currentTime += 20;
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                focused=ObjectQueue.QueueTrans.focusedObject();
                if(focused!=null) {
                    System.out.println(focused.name);
                    focused.picked= true;
                }
                //UPDATE
                scene.loopRender();
                ObjectQueue.Queue.toHead();
                while (ObjectQueue.Queue.object != null) {
                    ObjectQueue.Queue.object.figure.update();
                    ObjectQueue.Queue.next();
                }
                ObjectQueue.QueueTrans.sortByNear();
                ObjectQueue.QueueTrans.toHead();
                while (ObjectQueue.QueueTrans.object != null) {
                    ObjectQueue.QueueTrans.object.figure.update();
                    ObjectQueue.QueueTrans.next();
                }
                ObjectQueue.Queue.toHead();
                while (ObjectQueue.Queue.object != null) {
                    ObjectQueue.Queue.object.figure.picked=false;
                    ObjectQueue.Queue.next();
                }
                Camera.update();

                update();
                glfwSwapBuffers(window);
            }
        }
        glfwDestroyWindow(window);
        glfwTerminate();
    }


    boolean lostStateMouse=false;
    double mouseX,mouseY;
    float cameraAngleX=0,cameraAngleY=0;
    float distanse=-10;
    public void update(){
        FigureObject.mouseVec.set(2*(float)mouse.x/widthWindow-1,1-2*(float)mouse.y/heightWindow,-1f);
        FigureObject.mouseVec= Camera.cameraProjection.invertR().multiplyR(FigureObject.mouseVec,1);
        FigureObject.mouseVec= Camera.cameraView.invertR().multiplyR(FigureObject.mouseVec,1);

        if(mouseButton.left==true) {
            if(lostStateMouse!=mouseButton.left){
                mouseX= mouse.x;
                mouseY= mouse.y;
                cameraAngleX= Camera.angle.x;
                cameraAngleY= Camera.angle.y;
                picked= focused;
            }
            Camera.angle.y= cameraAngleY+(float)((mouseX - mouse.x));
            Camera.angle.x= cameraAngleX+(float)((mouseY - mouse.y));
        }
        lostStateMouse= mouseButton.left;


        if(picked!=null){
            if (KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
                picked.pos.z+=0.2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
                picked.pos.z-=0.2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
                picked.pos.x-=0.2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
                picked.pos.x+=0.2;
            }

            if (KeyboardHandler.isKeyDown(GLFW_KEY_Q)) {
                picked.pos.y+=0.2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_E)) {
                picked.pos.y-=0.2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_Z)) {
                picked.angle.y-=2;
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_X)) {
                picked.angle.y+=2;
            }
        }
        else {
            if (KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
                Camera.pos.z += 0.1 * Math.cos(Math.toRadians(Camera.angle.y))* Math.cos(Math.toRadians(Camera.angle.x));
                Camera.pos.x += 0.1 * Math.sin(Math.toRadians(Camera.angle.y))* Math.cos(Math.toRadians(Camera.angle.x));
                Camera.pos.y -= 0.1 * Math.sin(Math.toRadians(Camera.angle.x));
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
                Camera.pos.z -= 0.1 * Math.cos(Math.toRadians(Camera.angle.y))* Math.cos(Math.toRadians(Camera.angle.x));
                Camera.pos.x -= 0.1 * Math.sin(Math.toRadians(Camera.angle.y))* Math.cos(Math.toRadians(Camera.angle.x));
                Camera.pos.y += 0.1 * Math.sin(Math.toRadians(Camera.angle.x));
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
                Camera.pos.z -= 0.1 * Math.sin(Math.toRadians(Camera.angle.y));
                Camera.pos.x += 0.1 * Math.cos(Math.toRadians(Camera.angle.y));
            }
            if (KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
                Camera.pos.z += 0.1 * Math.sin(Math.toRadians(Camera.angle.y));
                Camera.pos.x -= 0.1 * Math.cos(Math.toRadians(Camera.angle.y));
            }
        }

    }

    public static void main(String... arg){
        new Main().run();
    }
}
