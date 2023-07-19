package com.maanraj514.utils;

import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * this class handles the files, load, copy etc.
 */
public class FileUtil {

    /*
    * Logger for logging messages.
    */
    private final Logger logger = Bukkit.getLogger();

    /**
     * loads the file specified.
     * returns the file loaded.
     * @param plugin the main plugin instance
     * @param resource the file to be loaded
     * @return the file loaded
     */
    public File loadFile(JavaPlugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            if (!folder.mkdir()){
                logger.severe("FAILED TO MAKE DIRECTORY " + folder.getName());
            }
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                if (!resourceFile.createNewFile()){
                    logger.severe("FAILED TO CREATE A NEW FILE " + resourceFile.getName());
                }
                try (InputStream in = plugin.getResource(resource);
                     OutputStream out = Files.newOutputStream(resourceFile.toPath())) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    /**
     * copies the source and pastes it in the destination.
     * @param source the file to be copied
     * @param destination the destination the file will be pasted in
     */
    public void copy(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                if (!destination.mkdir()){
                    logger.severe("FAILED TO MAKE DIRECTORY " + destination.getName());
                }
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
     * deletes the file specified.
     * @param file the file to be deleted
     */
    public void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File child : files) {
                delete(child);
            }
        }

        if (!file.delete()){
            logger.severe("FAILED TO DELETE FILE " + file.getName());
        }
    }
}