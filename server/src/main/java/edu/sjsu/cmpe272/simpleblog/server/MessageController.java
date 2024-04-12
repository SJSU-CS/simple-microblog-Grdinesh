package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> postMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        Map<String, Long> response = Collections.singletonMap("message-id", savedMessage.getMessageId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity<List<Message>> listMessages(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false, defaultValue = "-1") long msgId,
            @RequestBody(required = false) Map<String, Integer> requestBody) {
        if (limit == null && requestBody != null) {
            limit = requestBody.getOrDefault("limit", 10);
        }

        if (limit == null) {
            limit = 10; // Default limit is 10
        }

        if (msgId < 0 && requestBody != null) {
            msgId = requestBody.getOrDefault("msgId", -1);
        }
        if (limit > 20) {
            throw new IllegalArgumentException("Limit cannot exceed 20");
        }
        List<Message> messages = messageService.listMessages(limit, msgId);

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Message>> listMessagesGet(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false, defaultValue = "-1") int msgId,
            @RequestBody(required = false) Map<String, Integer> requestBody) {

        if (limit == null && requestBody != null) {
            limit = requestBody.getOrDefault("limit", 10);
        }

        if (limit == null) {
            limit = 10;
        }

        if (msgId < 0 && requestBody != null) {
            msgId = requestBody.getOrDefault("msgId", -1);
        }
        if (limit > 20) {
            throw new IllegalArgumentException("Limit cannot exceed 20");
        }
        List<Message> messages = messageService.listMessages(limit, msgId);

        return ResponseEntity.ok(messages);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}