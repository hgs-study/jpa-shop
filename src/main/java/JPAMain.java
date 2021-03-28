import domain_test.Member;
import domain_test.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            //Member m = em.find(Member.class,member.getId()); //JPA가 PK를 찾아서 최적화를 할 수 있다

            //JPQL은 쿼리문을 그대로 SQL로 번역이 된다. 따라서 MEMBER만 셀렉트되고 EAGER이기 때문에 TEAM도 셀렉트함
            //따라서 MEMBER 조회 1번 TEAM 조회 1번 => 총 2번 셀렉트문이 나감
            //N + 1 이란 조인된 TEAM N개  + 최초 쿼리 MEMBER 1개
            List<Member> members = em.createQuery("select m from Member m ", Member.class)
                    .getResultList();


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