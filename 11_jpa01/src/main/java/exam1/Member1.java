package exam1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JpaMember1")
public class Member1 {
	// long type goes to stack memory whereas
	// 'L'ong (Long) type goes to heap memory and thus allows null data
	// we use Long type for places where data can be null (and mostly instead of int for data to go into db)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private Long id; 
	

	private String username;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	public Member1 () {}
	
	public Member1(String username, LocalDate createDate) {
		// no id here since id gets auto-incremented
		this.username = username;
		this.createDate = createDate;
	}
}
