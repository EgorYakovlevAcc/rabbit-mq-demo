package org.hexlet.rabbitmqdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hexlet.rabbitmqdemo.dto.UserType;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date creationDate;
    private String author;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
