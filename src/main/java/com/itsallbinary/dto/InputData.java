package com.itsallbinary.dto;

import java.util.List;

public class InputData {
    private List<Audio> audio;   // Used in ASR tasks
    private List<Input> input;   // Used in Translation and TTS tasks

    // Getters and Setters
    public List<Audio> getAudio() { return audio; }
    public void setAudio(List<Audio> audio) { this.audio = audio; }

    public List<Input> getInput() { return input; }
    public void setInput(List<Input> input) { this.input = input; }
}


