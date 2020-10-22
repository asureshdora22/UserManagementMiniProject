package com.suresh.domain;

import lombok.Data;

@Data
public class UnlockAccount {

	private Integer id;
	private String emailId;
	private String TempPassword;
	private String newPwd;
	private String confirmPwd;
}
