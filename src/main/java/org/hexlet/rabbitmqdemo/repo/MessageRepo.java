package org.hexlet.rabbitmqdemo.repo;

import org.hexlet.rabbitmqdemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
