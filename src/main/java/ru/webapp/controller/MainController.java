package ru.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.webapp.domain.Task;
import ru.webapp.domain.User;
import ru.webapp.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private TaskRepository taskRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        Iterable<Task> tasks = taskRepository.findByAuthor(user);
        model.addAttribute("tasks", tasks);
        return "profile";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Task> tasks;

        if (filter != null && !filter.isEmpty()) {
            tasks = taskRepository.findByText(filter);
        } else {
            tasks = taskRepository.findAll();
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String university,
            @RequestParam String yearOfStudy,
            @RequestParam String type,
            @RequestParam String nameOfCourse,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Task task = new Task(text, tag, university, type, yearOfStudy, nameOfCourse, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            task.setFilenameForUser(file.getOriginalFilename());

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File("/" + uploadPath + "/" + resultFilename));
            task.setFilename(resultFilename);
        }

        taskRepository.save(task);

        Iterable<Task> tasks = taskRepository.findAll();
        model.put("tasks", tasks);
        return "main";
    }

    @GetMapping("/main/{id}/delete")
    public String deleteById(@PathVariable(value = "id") long id, Model model) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow();
        Files.delete(Paths.get(uploadPath + "/" + task.getFilename()));
        taskRepository.delete(task);
        return "redirect:/main";
    }


    @GetMapping("/main/{id}/download")
    public void downloadResource(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable(value = "id") Long id) throws IOException {

        Task task = taskRepository.findById(id).orElseThrow();
        File file = new File(uploadPath + "/" + task.getFilename());
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @GetMapping("/main/{id}/edit")
    public String editById(@PathVariable(value = "id") long id, Model model) throws IOException {
        if (!taskRepository.existsById(id)) {
            return "main";
        }
        Task task = taskRepository.findById(id).orElseThrow();

        model.addAttribute("task", task.getId());
        model.addAttribute("text", task.getText());
        model.addAttribute("tag", task.getTag());
        model.addAttribute("university", task.getUniversity());
        model.addAttribute("type", task.getType());
        model.addAttribute("nameOfCourse", task.getNameOfCourse());
        model.addAttribute("yearOfStudy", task.getYearOfStudy());
        model.addAttribute("filename", task.getFilename());

        return "taskEdit";
    }


    @PostMapping("/main/{id}/edit")
    public String edit(
            @PathVariable(value = "id") long id,
            @RequestParam String text,
            @RequestParam String university,
            @RequestParam String yearOfStudy,
            @RequestParam String type,
            @RequestParam String nameOfCourse,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setText(text);
        task.setUniversity(university);
        task.setCourse(yearOfStudy);
        task.setType(type);
        task.setNameOfCourse(nameOfCourse);
        task.setTag(tag);

        if(Files.exists(Paths.get(uploadPath + "/" + task.getFilename()))) {
            Files.delete(Paths.get(uploadPath + "/" + task.getFilename()));
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            task.setFilenameForUser(file.getOriginalFilename());

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File("/" + uploadPath + "/" + resultFilename));

            task.setFilename(resultFilename);
        }
        taskRepository.save(task);

        Iterable<Task> tasks = taskRepository.findAll();
        model.put("tasks", tasks);

        return "main";
    }
}