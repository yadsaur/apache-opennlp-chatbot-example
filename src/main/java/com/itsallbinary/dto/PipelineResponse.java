package com.itsallbinary.dto;

import java.util.List;

public class PipelineResponse {
    private List<TaskResponse> pipelineResponse;

    // Getters and Setters
    public List<TaskResponse> getPipelineResponse() { return pipelineResponse; }
    public void setPipelineResponse(List<TaskResponse> pipelineResponse) { this.pipelineResponse = pipelineResponse; }
}
