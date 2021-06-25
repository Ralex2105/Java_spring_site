package ru.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.webapp.domain.Task;
import ru.webapp.domain.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByText(String text);

    List<Task> findByAuthor(User author);


}