package org.andreis;

import java.io.FileOutputStream;
import java.io.IOException;

public class Message {
    String text;

    String nickwho;

    public Message(String text, String nickwho) {
        this.nickwho=nickwho;
        this.text=text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNickwho() {
        return nickwho;
    }

    public void setNickwho(String nickwho) {
        this.nickwho = nickwho;
    }
}
