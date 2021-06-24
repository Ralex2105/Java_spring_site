package ru.webapp.controller;

import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.webapp.domain.Message;
import ru.webapp.domain.User;
import ru.webapp.repository.MessageRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        Iterable<Message> messages = messageRepository.findByAuthor(user);
        model.addAttribute("messages", messages);
        return "profile";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByText(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
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
        Message message = new Message(text, tag, university, type, yearOfStudy, nameOfCourse, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            message.setFilenameForUser(file.getOriginalFilename());

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File("/" + uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);

        return "main";
    }

    @GetMapping("/main/{id}/delete")
    public String deleteById(@PathVariable(value = "id") long id, Model model) throws IOException {
        Message message = messageRepository.findById(id).orElseThrow();
        Files.delete(Paths.get(uploadPath + "/" + message.getFilename()));
        messageRepository.delete(message);
        return "redirect:/main";
    }


    @GetMapping("/main/{id}/download")
    public void downloadResource(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable(value = "id") Long id) throws IOException {

        Message message = messageRepository.findById(id).orElseThrow();
        File file = new File(uploadPath + "/" + message.getFilename());
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
        if (!messageRepository.existsById(id)) {
            return "main";
        }
        Message message = messageRepository.findById(id).orElseThrow();
        ArrayList<Message> res = new ArrayList<>();
        model.addAttribute("message", message.getId());
        model.addAttribute("text", message.getText());
        model.addAttribute("tag", message.getTag());
        model.addAttribute("university", message.getUniversity());
        model.addAttribute("type", message.getType());
        model.addAttribute("nameOfCourse", message.getNameOfCourse());
        model.addAttribute("yearOfStudy", message.getYearOfStudy());
        model.addAttribute("filename", message.getFilename());

        return "messageEdit";
    }


    @PostMapping("/main/{id}/edit")
    public String edit(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String university,
            @RequestParam String yearOfStudy,
            @RequestParam String type,
            @RequestParam String nameOfCourse,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setText(text);
        message.setUniversity(university);
        message.setCourse(yearOfStudy);
        message.setType(type);
        message.setNameOfCourse(nameOfCourse);
        message.setTag(tag);

        if(Files.exists(Paths.get(uploadPath + "/" + message.getFilename()))) {
            Files.delete(Paths.get(uploadPath + "/" + message.getFilename()));
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            message.setFilenameForUser(file.getOriginalFilename());

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File("/" + uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);

        return "main";
    }
}