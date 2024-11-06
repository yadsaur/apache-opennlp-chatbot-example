package com.itsallbinary.dto;

public class Language {
    private String sourceLanguage;
    private String targetLanguage;  // Optional field for translation tasks
    private String sourceScriptCode;  // New field for TTS response

    // Getters and Setters
    public String getSourceLanguage() { return sourceLanguage; }
    public void setSourceLanguage(String sourceLanguage) { this.sourceLanguage = sourceLanguage; }

    public String getTargetLanguage() { return targetLanguage; }
    public void setTargetLanguage(String targetLanguage) { this.targetLanguage = targetLanguage; }
    
    public String getSourceScriptCode() { return sourceScriptCode; }
    public void setSourceScriptCode(String sourceScriptCode) { this.sourceScriptCode = sourceScriptCode; }

}
