package myjpa1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
 * JPA에서는 클래스명을 통해 테이블을 생성한다.
 * 멤버변수는 컬럼을 생성하게 되는데,여러가지 어노테이션을 통해 세부적인
 * 설정이 가능하다.
 * 
 * @Entity : 클래스명으로 테이블을 생성한다.
 * @Table : 테이블 생성시 이름을 지정할 수 있다.
 * 즉, 별도의 지정이 없으면 클래스명이 default 테이블명이 된다.
 * */
@Entity
@Table(name = "JpaMember1")
public class Member1 {

    @Id
    @GeneratedValue
    private Long id;

    /*컬럼 생성시 별도의 지정이 없으면 변수명으로 생성한다.
     * 하지만 아래와 같이 @Column 어노테이션으로 컬럼명을 지정할 수 있다. */
    @Column(name = "create_data")
    private LocalDate createDate;

    private String username;

    /*jpa가 디폴트 생성자를 사용하므로 반드시 추가해야한다.
     * 만약 인수생성자를 정의하지 않았다면 자동으로 생성되므로 생략할 수 있다.*/
    public Member1() {
    }

    public Member1(String username, LocalDate createDate) {
        this.username = username;
        this.createDate = createDate;
    }
}
