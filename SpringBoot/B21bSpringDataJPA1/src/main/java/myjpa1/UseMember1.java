package myjpa1;

import java.time.LocalDate;

import jakarta.persistence.Entity;
// 필요에 따라 jakarta.persistence 혹은 javax.persistence 사용
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;

@Entity
@Table(name="JpaMember1")
public class UseMember1 {
	/*
	 * @Id : 컬럼 생서시 primary key로 지정됨.
	 * @GeneratedValue : 기본키에서 사용할 시퀀스 생성.
	 * 	별도의 설정이 없으면 증가치를 50으로 설정함.
	 * 	시퀀스명은 "테이블명_seq"로 지정됨.
	 *  만약 MySQL으 사용한다면 auto_increment(자동증가) 커럶으로
	 *  지정된다.
	 * */
    public static void main(String[] args) {
    	//이와같이 영속성을 생성한다.
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("MyJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        /*순수 jpa를 사용할때는 이정도의 코드가 있다는 것만 확인하고 넘어가면 된다.
         * 차후 spring-data-jpa로 넘어가면 아래 부분은 모두 추상화되어 직접 작성할
         * 일이 없기때문.*/
        try {
        	//트랜젝션 시작.
            transaction.begin();
            //테이블 및 시퀀스 생성을 위한 인스턴스 생성
            //인자생성자를 통해 테이블에 레코드도 입력된다.
            Member1 member1 = new Member1("홍길동1", LocalDate.now());
            //영속성 개체로 Member1을 전달하여 추가한다.
            em.persist(member1);
            //JPA가 오라클에 테이블을 생성하면서 동기화된다.
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
