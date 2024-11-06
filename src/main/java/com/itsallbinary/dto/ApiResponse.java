package com.itsallbinary.dto;

import java.util.List;

public class ApiResponse {
    private List<PipelineResponse> pipelineResponse;

    public List<PipelineResponse> getPipelineResponse() {
        return pipelineResponse;
    }

    public void setPipelineResponse(List<PipelineResponse> pipelineResponse) {
        this.pipelineResponse = pipelineResponse;
    }
}

