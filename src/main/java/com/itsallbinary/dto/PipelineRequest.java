package com.itsallbinary.dto;

import java.util.List;

public class PipelineRequest {
    private List<PipelineTask> pipelineTasks;
    private InputData inputData;

    // Getters and Setters
    public List<PipelineTask> getPipelineTasks() { return pipelineTasks; }
    public void setPipelineTasks(List<PipelineTask> pipelineTasks) { this.pipelineTasks = pipelineTasks; }

    public InputData getInputData() { return inputData; }
    public void setInputData(InputData inputData) { this.inputData = inputData; }
}

