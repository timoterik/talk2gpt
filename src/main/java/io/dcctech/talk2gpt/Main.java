/*
 * Copyright © 2022 - 2023, DCCTech. All Rights Reserved.
 * This copyright notice is the exclusive property of DCCTech and is hereby granted to users
 * for use of DCCTech's intellectual property.
 * Any reproduction, modification, distribution, or other use of DCCTech's intellectual property without prior written
 * consent is strictly prohibited.
 */

package io.dcctech.talk2gpt;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Integer exitCode = null;

            SpeechRecognition speech = SpeechRecognition.class.newInstance();
            try {
                speech.install();

                while (exitCode != null){
                    speech.recognizeSpeech();
                }
            } catch (Throwable t){
                exitCode = 0;
        }

    }
}