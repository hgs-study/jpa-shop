import domain_test.Member;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member =  new Member();
            member.setName("현건수");

            em.persist(member);
            em.flush();
            em.clear();

            Member reference2 = em.getReference(Member.class,member.getId());
            System.out.println("reference2 : "+ reference2.getClass());


            Hibernate.initialize(reference2);
            //reference2.getName(); //사실 강제 초기화
            System.out.println("프록시 초기화 확인 :"+ emf.getPersistenceUnitUtil().isLoaded(reference2));
            System.out.println("프록시 클래스 확인 :"+ reference2.getClass());
            System.out.println("프록시 강제 초기화 : Hibernate.initialize(reference2)");



            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }



}