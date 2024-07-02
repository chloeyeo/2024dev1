package exam4;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test02_typedQuery {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			TypedQuery<Member4> query = em.createQuery("select m from Member4 m order by m.name", Member4.class);
			List<Member4> result = query.getResultList();
			
			if (result.isEmpty()) {
				System.out.println("User does not exist.");
			} else {
				//  System.out.printf does not automatically append a newline (\n) at the end of the output. 
				result.forEach(user->System.out.printf("User Email: %s, User Name: %s, Created Date: %tY-%<tm-%<td\n",
						user.getEmail(), user.getName(), user.getCreateDate()));
			}
			
			transaction.commit();
			System.out.println("printed user!");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}
}
