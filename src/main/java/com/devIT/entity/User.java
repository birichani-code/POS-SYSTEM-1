package com.devIT.entity;

import com.devIT.entity.security.Authority;
import com.devIT.entity.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable = false, updatable = false)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	@Column(name="email", nullable = false, updatable = false)
	private String email;
	private String phone;
	private boolean enabled=true;
	

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private List<Order> orderList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));
		
		return authorites;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
