package com.pixelthieves.core.graphics;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Exception throw by shader compilation.
 */
public class ShaderException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4694794243512334997L;
    private static final String emptyLog = "--From Fragment Shader:\n--From Vertex Shader:\n";

    public ShaderException(String name, String log) {
        super("Compilation of shader [" + name + "] was unsuccessful.\n"+log);
    }

    public static ShaderException getInstance(String name, ShaderProgram shader){
        String log = shader.getLog();
        if(log.equals(emptyLog)){
           /* AndroidGL20 gl2 = new AndroidGL20();
            Log.e(TAG, gl2.glGetShaderInfoLog(shader));  */

            log = "No log information found, this is a bug on android.";
        }
        return new ShaderException(name, log);
    }

}
