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


            Member findMember = em.find(Member.class, member.getId());
            //Member findMember = em.getReference(Member.class,member.getId()); //프록시 클래스 (하이버네이트 사용)
            System.out.println("findMember : "+findMember.getClass()); // findMember : class domain_test.Member$HibernateProxy$Sg3az2nX

            Member reference = em.getReference(Member.class,member.getId());
            System.out.println("reference : "+ reference.getClass());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }



}