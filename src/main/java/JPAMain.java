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


//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class,member.getId()); //프록시 클래스 (하이버네이트 사용)
            System.out.println("findMember : "+findMember.getClass()); // findMember : class domain_test.Member$HibernateProxy$Sg3az2nX
//            System.out.println("findMember.id :" + findMember.getId());
            System.out.println("findMember.name 11: " + findMember.getName());
            System.out.println("findMember.name 22: " + findMember.getName()); // 프록시 target에 엔티티가 할당되어 있으니 다시 select문 안나감

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println(member.getName());
    }

}