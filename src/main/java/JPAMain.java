import Cascade.Child;
import Cascade.Parent;
import Embeded.Address;
import Embeded.Period;
import Embeded.User;
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

            User user = new User();
            user.setName("현건수");
            user.setHomeAddress(new Address("homeCity","street","zipcode"));

            user.getFavoriteFoods().add("치킨"); //insert문 나감
            user.getFavoriteFoods().add("족발"); //insert문 나감
            user.getFavoriteFoods().add("피자"); //insert문 나감 -> insert 총 3개 나감

            user.getAddressHistory().add(new Address("old1","street1","zipcode1"));
            user.getAddressHistory().add(new Address("old2","street2","zipcode2"));

            em.persist(user); //user를 persist 하면 컬렉션은 다른 테이블인데도 불구하고 같은 라이프 사이클을 가짐
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