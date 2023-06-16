package com.Project.project.model;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;
    private String title;
    private String videoConferenceLink;

    public String getVideoConferenceLink() {
        return videoConferenceLink;
    }

    public void setVideoConferenceLink(String videoConferenceLink) {
        this.videoConferenceLink = videoConferenceLink;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public Lesson(String title, String videoConferenceLink, Course course, String videoUrl, String documentId) {
        this.title = title;
        this.videoConferenceLink = videoConferenceLink;
        this.course = course;
        this.videoUrl = videoUrl;
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    private String videoUrl;
    private String documentId;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Lesson() {
    }

}
