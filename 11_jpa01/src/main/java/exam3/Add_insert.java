package exam3;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Add_insert {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaEx01");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			
			Member3 user = new Member3("newUser1@mail.com", "newUser1"); // = entity mapping: converting user data into the Member3 'Entity'
			// later on we can use @Builder annotation to the Entity class i.e. Member3 class then we don't have to manually make many
			// different constructors e.g. of different lengths, and later on persist() becomes save()
			// jpa -> hibernate (implementation of our jpa api calls (like persist()) run behind)
			// from form we get DTO -> then convert this DTO to an Entity
			// all Entities must have both empty constructor and constructor with fields so that we can store it in the database.
			// For exam3 we have not used dto to receive data
			// proper 'Entity Mapping' = getting form data as DTO and then convert DTO to an entity (and thus convert data to an Entity
			// which we can then store in the db).
			
			entityManager.persist(user);
			
			transaction.commit();
			System.out.println("inserted user!");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		
		emf.close();

	}

}
