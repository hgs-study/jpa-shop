import domain_test.Member;
import domain_test.Team;
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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member =  new Member();
            member.setName("현건수");
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            Member m = em.find(Member.class,member.getId()); // 지연로딩 때문에 Team은 조인 안하고 MEMBER만 조회
            System.out.println(m.getTeam().getClass());
            System.out.println("============");
            System.out.println(m.getTeam().getName());
            System.out.println("============");


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