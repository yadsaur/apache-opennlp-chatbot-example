package com.itsallbinary.dto;

public class ResponseConfig {
    private String audioFormat;
    private Language language;
    private String encoding;
    private int samplingRate;

    // Getters and Setters
    public String getAudioFormat() { return audioFormat; }
    public void setAudioFormat(String audioFormat) { this.audioFormat = audioFormat; }

    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    public String getEncoding() { return encoding; }
    public void setEncoding(String encoding) { this.encoding = encoding; }

    public int getSamplingRate() { return samplingRate; }
    public void setSamplingRate(int samplingRate) { this.samplingRate = samplingRate; }
}

