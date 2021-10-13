package com.verborum.boilerplate;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Engine {
    private long window;
    private final int windowWidth;
    private final int windowHeight;
    private final String title;

    public Engine(int windowWidth, int windowHeight, String title) {
        this.windowWidth  = windowWidth;
        this.windowHeight = windowHeight;
        this.title        = title;
    }

    public void run() {
        init();
        draw();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(this.windowWidth, this.windowHeight, this.title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( action == GLFW_RELEASE ) {
                switch (key) {
                    case GLFW_KEY_ESCAPE    -> glfwSetWindowShouldClose(window, true);
                    // TODO: add keys logic
                }
            }
        });

        glfwSetCursorPosCallback(window, (window, posX, posY) -> {
            // TODO: add cursor pos logic
        });

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            // TODO: add cursor buttons logic
        });

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    private void draw() {
        GL.createCapabilities();
        GL11.glClearColor(0.2f, 0.2f, 0.8f, 0.0f);

        while ( !glfwWindowShouldClose(window) ) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // TODO: add draw logic

            org.lwjgl.glfw.GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }
}
