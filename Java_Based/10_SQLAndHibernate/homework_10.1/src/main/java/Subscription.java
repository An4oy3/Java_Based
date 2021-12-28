import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {
    @Id
    private SubscriptionKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Student getStudent() {
        return student;
    }

    public void setStudentId(Student student) {
        this.student = student;
    }

    public Course getCourseId() {
        return course;
    }

    public void setCourseId(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
