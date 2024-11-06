package com.itsallbinary.dto;

public class Config {
    private Language language;
    private String serviceId;
    private String audioFormat;     // Optional field for ASR tasks
    private int samplingRate;       // Shared field for ASR and TTS tasks
    private String gender;          // Optional field for TTS tasks

    // Getters and Setters
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public String getAudioFormat() { return audioFormat; }
    public void setAudioFormat(String audioFormat) { this.audioFormat = audioFormat; }

    public int getSamplingRate() { return samplingRate; }
    public void setSamplingRate(int samplingRate) { this.samplingRate = samplingRate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}


