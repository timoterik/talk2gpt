/*
 * Copyright © 2022 - 2023, DCCTech. All Rights Reserved.
 * This copyright notice is the exclusive property of DCCTech and is hereby granted to users
 * for use of DCCTech's intellectual property.
 * Any reproduction, modification, distribution, or other use of DCCTech's intellectual property without prior written
 * consent is strictly prohibited.
 */

package io.dcctech.talk2gpt;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;

public class SpeechRecognition {

    String fileName = "var/file.raw";
    SpeechClient speechClient;
    ByteString audioBytes;
    RecognitionConfig config;
    RecognitionAudio audio;

    public void install() throws IOException {
        System.out.println("install");
        try {
            // Instantiates a client
            SpeechClient speechClient = SpeechClient.create();
            audioBytes = ByteString.readFrom(new FileInputStream("audio.raw"));

            // Configure request with local raw PCM audio
            config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("en-US")
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();
        } catch (Throwable t){
            System.out.println(t.getLocalizedMessage());
            throw t;
        }

    }

    public void recognizeSpeech(){
        RecognizeRequest request = RecognizeRequest.newBuilder()
                .setConfig(config)
                .setAudio(audio)
                .build();

        // Recognize speech
        SpeechRecognitionResult result = speechClient.recognize(request).getResults(0);
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
        String spokenText = alternative.getTranscript();

        System.out.printf("Transcription: %s%n",alternative.getTranscript());
    }



    public Integer finish() {
        speechClient.close();
        return -1;
    }


}
