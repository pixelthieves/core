package com.pixelthieves.core.graphics;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that is responsible for loading and baking shaders.
 */
public class Shaders implements Disposable {
    private final HashMap<String, ShaderStructure> map = new HashMap<String, ShaderStructure>();

    public Shaders() {
        FileHandle[] list = getFiles();
        for (FileHandle file : list) {
            processFile(file, true);
        }
    }

    private void processFile(FileHandle file, boolean silent) {
        String ext = file.extension();
        String name = file.nameWithoutExtension();

        ShaderStructure ss;
        if (!map.containsKey(name)) {
            ss = new ShaderStructure(name);
        } else {
            ss = map.get(name);
        }

        switch (ShaderType.valueOf(ext)) {
            case vsh:
                ss.setVertex(file.readString());
                break;
            case fsh:
                ss.setFragment(file.readString());
                break;
        }
        if (silent) {
            try {
                ss.compile();
            } catch (ShaderException e) {
                System.out.println("Warning: " + e.getMessage());
            }
        } else {
            ss.compile();
        }

        if (!map.containsKey(name)) {
            map.put(name, ss);
        }
    }

    private FileHandle[] getFiles() {
        FileHandle[] files;
        FileHandle dirHandle;

        // Eclipse side-effect of linking assets, assets are copied in bin folder
        if (Gdx.app.getType() == ApplicationType.Android) {
            files = Gdx.files.internal("data/shaders").list();
        } else {
            // ApplicationType.Desktop ..
            dirHandle = Gdx.files.internal("./bin/data/shaders");
            if (!dirHandle.exists()) {
                files = Gdx.files.internal("data/shaders").list();
            } else {
                files = dirHandle.list();
            }
        }
        return files;
    }

    /**
     * Returns a shader with corresponding name.
     *
     * @param name of the shader
     * @return a baked shader
     */
    public ShaderProgram getShader(String name) {
        return getStructure(name).getShader();
    }

    private ShaderStructure getStructure(String name) {
        ShaderStructure value = map.get(name);
        if (value == null) {
            processFile(Gdx.files.internal("data/shaders/" + name + ".vsh"), true);
            processFile(Gdx.files.internal("data/shaders/" + name + ".fsh"), true);
            value = map.get(name);
            if (value == null) {
                throw new IllegalArgumentException("Specified Shader does not exists: " + name);
            }
        }
        return value;
    }

    public void dispose() {
        for (Map.Entry<String, ShaderStructure> entry : map.entrySet()) {
            ShaderProgram shader = entry.getValue().getShader();
            if(shader != null){
            shader.dispose();
            }
        }
    }

    private enum ShaderType {
        vsh, fsh
    }

    private static class ShaderStructure {
        private final String name;
        private String vertex;
        private String fragment;
        private ShaderProgram program;

        public ShaderStructure(String name) {
            this.name = name;
        }

        public void setVertex(String vertex) {
            this.vertex = vertex;
        }

        public void setFragment(String fragment) {
            this.fragment = fragment;
        }

        private void compile() {
            if (vertex != null && fragment != null) {
                System.out.println("Compiling shader [" + name + "]");
                program = new ShaderProgram(vertex, fragment);
                if (!program.isCompiled()) {
                    ShaderException instance = ShaderException.getInstance(name, program);
                    program = null;
                    throw instance;
                }
            }
        }

        public ShaderProgram getShader() {
            return program;
        }

    }

}
