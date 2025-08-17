package com.example.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ForgetPasswordRequest {
	private String username;
	private String nickName;
	private String newPassword;
}
