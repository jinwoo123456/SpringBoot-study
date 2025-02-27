package myjpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UseMember03_update {

    public static void main(String[] args) {

        // 영속성 인스턴스 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyJPA");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Member3 member3 = em.find(Member3.class, "hong@spring.com");
            if (member3 == null) {
                System.out.println("존재하지 않습니다.");
                transaction.rollback();
                return;
            }

            member3.changeName("전우치");
            transaction.commit();
            System.out.println("이름을 변경했습니다.");

        } catch (Exception e) {
            transaction.rollback();
            throw e;

        } finally {
            em.close();
            emf.close();
        }
    }
}
