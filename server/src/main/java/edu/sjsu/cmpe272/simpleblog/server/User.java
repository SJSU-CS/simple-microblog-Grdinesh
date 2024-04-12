package edu.sjsu.cmpe272.simpleblog.server;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @JsonProperty("user")
    private String username;

    @JsonProperty("public-key")
    @Column(length = 2048)
    private String publicKey;

    public User() {}

    public User(String username, String publicKey) {
        this.username = username;
        this.publicKey = publicKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPublicKey() {
        return this.publicKey;
    }
}
