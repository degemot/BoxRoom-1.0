package utils;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {
    public static FloatBuffer getBuffer(float[] array) {
        FloatBuffer result = BufferUtils.createFloatBuffer(array.length);
        result.put(array).flip();
        return result;
    }

    public static IntBuffer getBuffer(int[] array) {
        IntBuffer result = BufferUtils.createIntBuffer(array.length);
        result.put(array).flip();
        return result;
    }
}
