package com.itsallbinary.serviceImpl;

import java.util.Arrays;

import com.itsallbinary.dto.PipelineRequest;
import com.itsallbinary.dto.Config;
import com.itsallbinary.dto.Language;
import com.itsallbinary.dto.PipelineTask;
import com.itsallbinary.dto.Input;
import com.itsallbinary.dto.InputData;



public class PipelineRequestCreator {

    public static PipelineRequest createPipelineRequest(String task, String sourceLanguage, String serviceId, String sourceText) {
        // Set up Language
        Language language = new Language();
        language.setSourceLanguage(sourceLanguage);

        // Set up Config
        Config config = new Config();
        config.setLanguage(language);
        config.setServiceId(serviceId);
        config.setGender("female");
        config.setSamplingRate(8000);

        // Set up PipelineTask
        PipelineTask pipelineTask = new PipelineTask();
        pipelineTask.setTaskType(task);
        pipelineTask.setConfig(config);

        // Set up Input
        Input input = new Input();
        input.setSource(sourceText);

        // Set up InputData
        InputData inputData = new InputData();
        inputData.setInput(Arrays.asList(input));

        // Set up PipelineRequest
        PipelineRequest request = new PipelineRequest();
        request.setPipelineTasks(Arrays.asList(pipelineTask));
        request.setInputData(inputData);

        return request;
    }
}
