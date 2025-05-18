package com.itsallbinary.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsallbinary.dto.ApiResponse;
import com.itsallbinary.dto.Audio;
import com.itsallbinary.dto.Config;
import com.itsallbinary.dto.Input;
import com.itsallbinary.dto.InputData;
import com.itsallbinary.dto.Language;
import com.itsallbinary.dto.PipelineRequest;
import com.itsallbinary.dto.PipelineResponse;
import com.itsallbinary.dto.PipelineTask;
import com.itsallbinary.serviceImpl.OpenNLPLocal;

@RestController
@RequestMapping("/voicebot")
public class VoiceController {

	private static final String API_URL = "https://dhruva-api.bhashini.gov.in/services/inference/pipeline";
	private static final String SERVICE_ID_ASR = "ai4bharat/conformer-hi-gpu--t4";
	private static final String SERVICE_ID_TTS_EN = "ai4bharat/indic-tts-coqui-misc-gpu--t4";
	private static final String SERVICE_ID_TTS_HI = "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4";
	private static final String SERVICE_ID_TRANSLATE_HI_TO_EN = "ai4bharat/indictrans-v2-all-gpu--t4";
	private static final String SERVICE_ID_TRANSLATE_EN_TO_HI = "ai4bharat/indictrans-v2-all-gpu--t4";
    private static final String AUTHORIZATION_TOKEN = "pEogDoH6FnQhjON7W96439rtTxCgy4MG3sv80S-cbJ6CUzW8CjP2qICi8M_I2ELq"; // Replace with your actual token

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private OpenNLPLocal openNLPLocal;
    
    @CrossOrigin(origins = "*") 
    @PostMapping("/tts")
    public String tts(@RequestBody String content) throws Exception {

    	String requestBody = ttsRequest("en", SERVICE_ID_TTS_EN, content).toString();
        PipelineResponse pipelineResponse = makeApiCall(requestBody);
        String answerWav = pipelineResponse.getPipelineResponse().get(0).getAudio().get(0).getAudioContent();

        return answerWav;
    }


    @PostMapping("/sendRequest")
    public ResponseEntity<String> sendRequest(@RequestBody String requestBody) throws FileNotFoundException, IOException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("application/json"));
        headers.set("Authorization", AUTHORIZATION_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            API_URL,
            HttpMethod.POST,
            entity,
            String.class
        );
        ApiResponse apiResponse = objectMapper.readValue(response.toString(), ApiResponse.class);
//        String answer = openNLPLocal.answer(apiResponse.getPipelineResponse().get(1).getOutput().get(0).getTarget());

        return response;
    }
    
    @PostMapping("/processRequest")
    public String process(@RequestBody String base64) throws Exception {
        String requestBody = asrRequest("hi", SERVICE_ID_ASR, base64);
    	PipelineResponse pipelineResponse = makeApiCall(requestBody);
    	
    	String text = pipelineResponse.getPipelineResponse().get(0).getOutput().get(0).getSource();
        requestBody = translationRequest("hi", "en", SERVICE_ID_TRANSLATE_HI_TO_EN, text).toString();
        pipelineResponse = makeApiCall(requestBody);
        
        String answer = openNLPLocal.answer(pipelineResponse.getPipelineResponse().get(0).getOutput().get(0).getTarget());
        
        requestBody = translationRequest("en", "hi", SERVICE_ID_TRANSLATE_EN_TO_HI, answer).toString();
        pipelineResponse = makeApiCall(requestBody);
    	String answerTranslated = pipelineResponse.getPipelineResponse().get(0).getOutput().get(0).getTarget();

    	requestBody = ttsRequest("hi", SERVICE_ID_TTS_HI, answerTranslated).toString();
        pipelineResponse = makeApiCall(requestBody);
        String answerWav = pipelineResponse.getPipelineResponse().get(0).getAudio().get(0).getAudioContent();

        return answerWav;
    }
    
    public PipelineResponse makeApiCall(String requestBody) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("application/json"));
        headers.set("Authorization", AUTHORIZATION_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
        	API_URL,
            HttpMethod.POST,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return objectMapper.readValue(response.getBody(), PipelineResponse.class);
        } else {
            throw new RuntimeException("Failed to retrieve data from API: " + response.getStatusCode());
        }
    }
    
    public String asrRequest(String lang, String serviceId, String base64) throws JsonProcessingException {
    	PipelineRequest asrRequest = new PipelineRequest();

    	Language asrLanguage = new Language();
    	asrLanguage.setSourceLanguage(lang);

    	Config asrConfig = new Config();
    	asrConfig.setLanguage(asrLanguage);
    	asrConfig.setServiceId(serviceId);
    	asrConfig.setAudioFormat("flac");
    	asrConfig.setSamplingRate(16000);

    	PipelineTask asrTask = new PipelineTask();
    	asrTask.setTaskType("asr");
    	asrTask.setConfig(asrConfig);

    	Audio audio = new Audio();
    	audio.setAudioContent(base64);

    	InputData asrInputData = new InputData();
    	asrInputData.setAudio(Arrays.asList(audio));

    	asrRequest.setPipelineTasks(Arrays.asList(asrTask));
    	asrRequest.setInputData(asrInputData);
    	return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(asrRequest);
    }
    
    public String ttsRequest(String lang, String serviceId, String text) throws JsonProcessingException {
    	PipelineRequest ttsRequest = new PipelineRequest();

    	Language ttsLanguage = new Language();
    	ttsLanguage.setSourceLanguage(lang);

    	Config ttsConfig = new Config();
    	ttsConfig.setLanguage(ttsLanguage);
    	ttsConfig.setServiceId(serviceId);
    	ttsConfig.setGender("female");
    	ttsConfig.setSamplingRate(8000);

    	PipelineTask ttsTask = new PipelineTask();
    	ttsTask.setTaskType("tts");
    	ttsTask.setConfig(ttsConfig);

    	Input ttsInput = new Input();
    	ttsInput.setSource(text);

    	InputData ttsInputData = new InputData();
    	ttsInputData.setInput(Arrays.asList(ttsInput));

    	ttsRequest.setPipelineTasks(Arrays.asList(ttsTask));
    	ttsRequest.setInputData(ttsInputData);
    	return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ttsRequest);
    }
    
    public String translationRequest(String sourceLang, String targetLang, String serviceId, String text) throws JsonProcessingException {
    	PipelineRequest translationRequest = new PipelineRequest();

    	Language translationLanguage = new Language();
    	translationLanguage.setSourceLanguage(sourceLang);
    	translationLanguage.setTargetLanguage(targetLang);

    	Config translationConfig = new Config();
    	translationConfig.setLanguage(translationLanguage);
    	translationConfig.setServiceId(serviceId);

    	PipelineTask translationTask = new PipelineTask();
    	translationTask.setTaskType("translation");
    	translationTask.setConfig(translationConfig);

    	Input translationInput = new Input();
    	translationInput.setSource(text);

    	InputData translationInputData = new InputData();
    	translationInputData.setInput(Arrays.asList(translationInput));

    	translationRequest.setPipelineTasks(Arrays.asList(translationTask));
    	translationRequest.setInputData(translationInputData);
    	return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(translationRequest);
    }

}
