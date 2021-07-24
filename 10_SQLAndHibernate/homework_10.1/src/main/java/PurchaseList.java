import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class PurchaseList {//implements Serializable {
    @EmbeddedId
    @Id
    private PurchaseListKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private Student studentName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_name", insertable = false, updatable = false)
    private Course courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
