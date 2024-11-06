package com.itsallbinary.dto;

import java.util.List;

public class TaskResponse {
    private String taskType;
    private ResponseConfig config;
    private List<Output> output;
    private List<AudioContent> audio;

    // Getters and Setters
    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public ResponseConfig getConfig() { return config; }
    public void setConfig(ResponseConfig config) { this.config = config; }

    public List<Output> getOutput() { return output; }
    public void setOutput(List<Output> output) { this.output = output; }

    public List<AudioContent> getAudio() { return audio; }
    public void setAudio(List<AudioContent> audio) { this.audio = audio; }
}

