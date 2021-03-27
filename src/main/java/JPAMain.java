import domain_test.Member;

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

            em.detach(reference2);
            //em.close();
            reference2.getName();

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