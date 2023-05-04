package idusw.springboot.boardkjsh.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity // 엔티티 클래스임으로 나타내는 애노테이션
@Table(name = "member_a201812012")

@ToString   // lombok 라이브러리 사용
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@SequenceGenerator(sequenceName = "member_a201812012_seq", name = "member_a201812012_seq_gen",
        initialValue = 1, allocationSize = 1)
public class MemberEntity extends BaseEntity {
    // Entity : Service -> Repository -> Service 데이터 객체, Database 관점
    // Repository : Persistence Data 처리
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_a201812012_seq_gen")
    // Oracle : GenerationType.SEQUENCE, Mysql : GenerationType.IDENTITY
    // Mysql/MariaDB : auto increment , Oracle : sequence - 자동증가 식별번호
    private Long seq;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String pw;
}
