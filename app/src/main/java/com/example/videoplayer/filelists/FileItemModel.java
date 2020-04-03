package com.example.videoplayer.filelists;

import com.example.videoplayer.BuildConfig;
import com.google.gson.Gson;

import retrofit2.converter.gson.GsonConverterFactory;

public class FileItemModel {
    String path;
    String name;
    boolean isDirectory;
    String added;
    String modified;
    String  resolution;
    long length;
    private String videoRotation;
    private String duration;

    public FileItemModel(){

    }
    public FileItemModel(String path, String name, String added, String modified, String resolution) {
        this.path = path;
        this.name = name;
        this.added = added;
        this.modified = modified;
        this.resolution = resolution;
    }

    public FileItemModel(String name, String path, boolean isDirectory) {
        this.name = name;
        this.path = path;
        this.length=length;
        this.isDirectory = isDirectory;
    }

    public FileItemModel(String name, String path, long length, boolean isDirectory) {

        this.name = name;
        this.path = path;
        this.length = length;
        this.isDirectory = isDirectory;
    }

    public String getPath() {
        return path;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public void setVideoRotation(String rotation) {
        videoRotation = rotation;
    }

    public String getVideoRotation() {
        return videoRotation;
    }

    public void setDuration(String duration) {

        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
}
