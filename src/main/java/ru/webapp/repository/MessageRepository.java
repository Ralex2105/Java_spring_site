package ru.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.webapp.domain.Message;
import ru.webapp.domain.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByText(String text);

    List<Message> findByAuthor(User author);


}