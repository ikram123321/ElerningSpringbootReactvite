package com.Project.project.Controler;
import com.Project.project.Repository.CourseRepo;
import com.Project.project.Repository.LessonRepo;
import com.Project.project.ServiceImp.LessonServiceImp;
import com.Project.project.model.Course;
import com.Project.project.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LessonController {
    @Autowired
    private LessonServiceImp LService;
    @Autowired
    private LessonRepo Lrepo;
    @Autowired
    private CourseRepo courseRepo;

    @GetMapping("/getLesson")
    public List<Lesson> ListALLLessons(){
        return this.LService.listAllLesson();
    }

    @GetMapping("/findLessonbyid/{LessonID}")
    public ResponseEntity<Lesson> getCateByIdCat(@PathVariable("LessonID") int LessonID) {
        Lesson teach = LService.getLessonById(LessonID);
        return new ResponseEntity<>(teach, HttpStatus.OK);
    }
    @PostMapping("/addLesson")
    public ResponseEntity<Lesson> add(@RequestBody Lesson lesson) {
        // Retrieve the course object using the courseId from the lesson object
        Course course = courseRepo.findById(lesson.getCourse().getCourseId()).orElse(null);

        if (course != null) {
            // Set the course for the lesson
            lesson.setCourse(course);

            // Save the lesson to the database
            Lesson newLesson = LService.addLesson(lesson);
            return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
        } else {
            // Handle the case when the course is not found
            // You can return an appropriate response or handle it as per your application's requirements
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/lessonsByCourse/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourse(@PathVariable Long courseId) {
        // Retrieve the course object using the courseId
        Course course = courseRepo.findById(courseId).orElse(null);

        if (course != null) {
            // Retrieve the lessons associated with the course
            List<Lesson> lessons = LService.getLessonsByCourse(course);

            return new ResponseEntity<>(lessons, HttpStatus.OK);
        } else {
            // Handle the case when the course is not found
            // You can return an appropriate response or handle it as per your application's requirements
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deleteLesson/{LessonID}")
    public ResponseEntity<?> deleteLessonBYID(@PathVariable("LessonID") int LessonID) {
        LService.deleteLesson(LessonID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("updateLesson/{LessonID}")
    public Lesson updateLessonById(@PathVariable Long LessonID, @RequestBody Lesson updatedLesson) {
        return LService.update(LessonID, updatedLesson);
    }
}