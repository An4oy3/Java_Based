import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class LinkedPurchaseList{
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private Student student;

    @JoinColumn(name = "course_name", insertable = false, updatable = false)
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
