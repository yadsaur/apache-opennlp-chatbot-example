package com.itsallbinary.dto;

public class PipelineTask {
    private String taskType;
    private Config config;

    // Getters and Setters
    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public Config getConfig() { return config; }
    public void setConfig(Config config) { this.config = config; }
}

