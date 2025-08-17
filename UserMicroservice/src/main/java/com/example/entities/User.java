package com.example.entities;

import javax.persistence.*;

import com.example.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
	@Id
	@GeneratedValue
	private Long userId;
	@Column(unique = true)
	private String username;
	private String firstName;
	private String lastName;
	private Long phoneNo;
	private UserType userType;
	private String gender;
	private String nickName;
	private String password;
}