package edu.sjsu.cmpe272.simpleblog.server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageData extends JpaRepository<Message, Long> {
}
