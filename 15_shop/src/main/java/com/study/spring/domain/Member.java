package com.study.spring.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude="memberRoleList") // if putting many exclude, put it as object {} // use toStringOf when more members in memberRoleList than number of fields.
public class Member {
	@Id
	private String email;
	
	private String pw;
	private String nickname;
	private boolean social;
	
	@ElementCollection
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRoles() {
		memberRoleList.clear();
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setSocial(boolean social) {
		this.social = social;
	}	
}
