package ru.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.webapp.domain.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByText(String text);
}