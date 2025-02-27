package myjpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember2 {
    public static void main(String[] args) {
        // EntityManagerFactory 생성 (JPA 설정 파일에서 "MyJPA"라는 설정을 찾음)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");

        // EntityManager 생성 (데이터베이스 작업을 담당)
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 객체 가져오기
        EntityTransaction transaction = em.getTransaction();

        try {
            // 트랜잭션 시작
            transaction.begin();

            // Member2 객체 생성 및 persist(영속성 컨텍스트에 저장)
            Member2 member2 = new Member2("홍길동2", "1234");
            em.persist(member2);

            // 트랜잭션 커밋 (DB 반영)
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            // 예외 발생 시 롤백
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            // EntityManager 종료
            em.close();
        }

        // EntityManagerFactory 종료
        emf.close();
    }
}
