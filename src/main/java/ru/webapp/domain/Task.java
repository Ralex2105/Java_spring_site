package ru.webapp.domain;


import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;
    private String university;
    private String type;
    private String yearOfStudy;
    private String nameOfCourse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;
    private String filenameForUser;

    public Task() {
    }

    public Task(String text, String tag, String university, String type,
                String yearOfStudy, String nameOfCourse, User user) {
        this.author = user;
        this.text = text.trim();
        this.tag = tag.trim();
        this.university = university;
        this.type = type;
        this.yearOfStudy = yearOfStudy;
        this.nameOfCourse = nameOfCourse;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return yearOfStudy;
    }

    public void setCourse(String course) {
        this.yearOfStudy = course;
    }

    public String getFilenameForUser() {
        return filenameForUser;
    }

    public void setFilenameForUser(String filenameForUser) {
        this.filenameForUser = filenameForUser;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }
}