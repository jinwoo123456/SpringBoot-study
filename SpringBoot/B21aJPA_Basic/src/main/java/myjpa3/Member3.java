package myjpa3;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "JpaMember3")
public class Member3 {

    @Id
    private String email;

    private String name;

    @Column(name = "create_date")
    private LocalDate createDate;

    public Member3() {
    }

    //생성자
    public Member3(String email, String name, LocalDate createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    //게터
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    //이름을 변경하기 위한 메서드
    public void changeName(String newName) {
        this.name = newName;
    }
}
