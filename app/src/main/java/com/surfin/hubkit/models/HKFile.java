package com.surfin.hubkit.models;

import java.io.File;

public class HKFile {

    /**
     * The file's data
     */
    public File file; //TODO check this type

    /**
     * Name of the file, including extension.
     */
    public String   filename;

    /**
     * mimetype
     */
    public String   mimetype;

    /**
     * Create a new HKFile
     *
     * HKFile file = new HKFile("hello", "foo.txt", "application/octect-stream")
     *
     * @param file the file's content
     * @param filename name of the file
     * @param mimetype mimetype of the file
     */
    public HKFile(File file, String filename, String mimetype) {
        this.file = file;
        this.filename = filename;
        this.mimetype = mimetype;
    }
}
