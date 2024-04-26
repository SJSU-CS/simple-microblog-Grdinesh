package edu.sjsu.cmpe272.simpleblog.server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageData MessageData;

    public Message saveMessage(Message message) {
        return MessageData.save(message);
    }

    public List<Message> listMessages(Integer limit, long next) {
        List<Message> filteredMessages;
        
        // Fetch all messages
        List<Message> allMessages = MessageData.findAll();

        // If next is greater than 0, filter messages by ID less than or equal to 'next'
        if (next > 0) {
            filteredMessages = allMessages.stream()
                    .filter(message -> message.getMessageId() > next)
                    .collect(Collectors.toList());
        } else {
            filteredMessages = allMessages;
        }

        // Sort the filtered messages by messageId in descending order
        filteredMessages.sort((m1, m2) -> Long.compare(m2.getMessageId(), m1.getMessageId()));

        // Limit the number of messages based on the 'limit' parameter
        if (filteredMessages.size() > limit) {
            return filteredMessages.subList(0, limit);
        } else {
            return filteredMessages;
        }
    }
}
