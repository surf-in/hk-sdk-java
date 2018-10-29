package com.surfin.hubkit;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;
import java.net.URI;

public class HKFile
    extends File
{
    public HKFile(@NonNull String pathname) {
        super(pathname);
    }

    public HKFile(String parent, @NonNull String child) {
        super(parent, child);
    }

    public HKFile(File parent, @NonNull String child) {
        super(parent, child);
    }

    public HKFile(@NonNull URI uri) {
        super(uri);
    }

//    /**
//     * The file's data
//     */
//    public File file; //TODO check this type
//
//    /**
//     * Name of the file, including extension.
//     */
//    public String   filename;
//
//    /**
//     * mimetype
//     */
//    public String   mimetype;
//
//    /**
//     * Create a new HKFile
//     *
//     * HKFile file = new HKFile("hello", "foo.txt", "application/octect-stream")
//     *
//     * @param file the file's content
//     * @param filename name of the file
//     * @param mimetype mimetype of the file
//     */
//    public HKFile(File file, String filename, String mimetype) {
//        this.file = file;
//        this.filename = filename;
//        this.mimetype = mimetype;
//    }
}
