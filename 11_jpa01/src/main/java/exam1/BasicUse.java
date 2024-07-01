package exam1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BasicUse 
{
	public static void main(String[] args) 
	{
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("JpaEx01");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            
            Member1 user = new Member1("홍길동1", LocalDate.now()); // either use this or 'attribute' method to put this to ur data object.
            entityManager.persist(user); // save
            
            transaction.commit(); // this puts the data (from cache) into actual data.
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }

        emf.close();
	}
}