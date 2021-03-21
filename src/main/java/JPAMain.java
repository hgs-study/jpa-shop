import domain.Member;
import domain.Order;
import domain.OrderItem;

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
            //방법 1 (양방향 연관관계)
            //Order order = new Order();
            //order.addOrderItem(new OrderItem());


            //방법 2 (단방향 연관관계)
            //단방향 연관관계로도 애플리케이션을 만드는데에 지장이 없다.
            //양방향 연관관계로 하는 이유는 조회를 더 편하게 하기 위해서
            //나중에 JPQL로 조인을 더 복잡하게 해야하는데 이 작업을 더 간단히 하기위해 양방향으로 많이 짠다.
            //핵심은 단방향으로 할 수 있으면 최대한 단방향으로 해라!
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}