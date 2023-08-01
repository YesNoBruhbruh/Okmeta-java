package com.maanraj514.utils;

import com.google.common.io.ByteStreams;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

/**
 * This class handles the files, load, copy etc.
 */
public class FileUtil {

    /**
     * Loads the file specified.
     * Returns the file loaded.
     *
     * @param plugin the main plugin instance
     * @param resource the file to be loaded
     * @return the file loaded
     */
    public static File loadFile(@NotNull JavaPlugin plugin, @NotNull String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        }
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResource(resource);
                     OutputStream out = Files.newOutputStream(resourceFile.toPath())) {
                    if (in != null){
                        ByteStreams.copy(in, out);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    /**
     * Copies the source and pastes it in the destination.
     *
     * @param source the file to be copied
     * @param destination the destination the file will be pasted in
     */
    public static void copy(@NotNull File source, @NotNull File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdir();
            }

            String[] files = source.list();
            if (files == null) return;
            for (String file : files) {
                File newSource = new File(source, file);
                File newDestination = new File(destination, file);
                copy(newSource, newDestination);
            }
        } else {
            InputStream in = Files.newInputStream(source.toPath());
            OutputStream out = Files.newOutputStream(destination.toPath());

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    /**
     * Deletes the file specified.
     *
     * @param file the file to be deleted
     */
    public static void delete(@NotNull File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File child : files) {
                delete(child);
            }
        }
        file.delete();
    }
}