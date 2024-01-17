package harakiri.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;


    public UserCourses(String courseId, UserEntity user) {
        this.courseId = courseId;
        this.user = user;
    }
}
