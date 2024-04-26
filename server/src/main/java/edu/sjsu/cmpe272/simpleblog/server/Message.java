package edu.sjsu.cmpe272.simpleblog.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.OffsetDateTime;

@Getter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private OffsetDateTime date;
    private String author;
    private String message;
    private String attachment;

    @Column(length = 2048)
    private String signature;

    public Message() {
    }
    public Message(OffsetDateTime date, String author, String message, String attachment, String signature) {
        this.date = date;
        this.author = author;
        this.message = message;
        this.attachment = attachment;
        this.signature = signature;
    }

    @JsonProperty("message-id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
