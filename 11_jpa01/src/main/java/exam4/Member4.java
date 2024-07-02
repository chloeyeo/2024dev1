package exam4;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="JpaMember4")
@Getter
public class Member4 {
	@Id
	private String email;
	
	private String name;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	protected Member4() {}
	
	public Member4(String email, String name, LocalDate createDate) {
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}
	
	public void changeName(String newName) {
		name = newName;
	}
}
