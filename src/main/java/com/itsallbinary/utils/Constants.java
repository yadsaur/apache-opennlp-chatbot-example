package com.itsallbinary.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	
	public static final Map<String, String> ASR_ID_MAP;
	static {
		Map<String, String> map = new HashMap<>();
		map.put("as", "ai4bharat/whisper-medium-en--gpu--t4");
		map.put("bn", "ai4bharat/conformer-multilingual-indo_aryan-gpu--t4");
		map.put("gu", "ai4bharat/conformer-multilingual-indo_aryan-gpu--t4");
		map.put("hi", "ai4bharat/conformer-hi-gpu--t4");
		map.put("kn", "ai4bharat/conformer-multilingual-dravidian-gpu--t4");
		map.put("ml", "ai4bharat/conformer-multilingual-dravidian-gpu--t4");
		map.put("mr", "ai4bharat/conformer-multilingual-indo_aryan-gpu--t4");
		map.put("or", "ai4bharat/conformer-multilingual-indo_aryan-gpu--t4");
		map.put("pa", "ai4bharat/conformer-multilingual-indo_aryan-gpu--t4");
		map.put("ta", "ai4bharat/conformer-multilingual-dravidian-gpu--t4");
		map.put("te", "ai4bharat/conformer-multilingual-dravidian-gpu--t4");
		map.put("en", "ai4bharat/whisper-medium-en--gpu--t4");
		ASR_ID_MAP = Collections.unmodifiableMap(map);
	}
	
	public static final Map<String, String> TTS_ID_MAP;
	static {
		Map<String, String> map = new HashMap<>();
		map.put("as", "ai4bharat/indic-tts-coqui-misc-gpu--t4");
		map.put("bn", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("gu", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("hi", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("kn", "ai4bharat/indic-tts-coqui-dravidian-gpu--t4");
		map.put("ml", "ai4bharat/indic-tts-coqui-dravidian-gpu--t4");
		map.put("mr", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("or", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("pa", "ai4bharat/indic-tts-coqui-indo_aryan-gpu--t4");
		map.put("ta", "ai4bharat/indic-tts-coqui-dravidian-gpu--t4");
		map.put("te", "ai4bharat/indic-tts-coqui-dravidian-gpu--t4");
		map.put("en", "ai4bharat/indic-tts-coqui-misc-gpu--t4");
		TTS_ID_MAP = Collections.unmodifiableMap(map);
	}
	
	public static final Map<String, String> TRANSLATE_ID_MAP;
	static {
		Map<String, String> map = new HashMap<>();
		map.put("hi", "ai4bharat/indictrans-v2-all-gpu--t4");
		TRANSLATE_ID_MAP = Collections.unmodifiableMap(map);
	}
	
}