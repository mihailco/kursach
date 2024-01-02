package harakiri.entity;

import jakarta.persistence.*;

@Entity
public class UserCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;
}
