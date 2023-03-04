package com.user.userops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data

public class ReqRes {

	private int page;
	private int perPage;
	private int totalPage;
	private int total;
	private UserData userData;
	private Support support;

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder(toBuilder = true)
	@Data

	public static class UserData
	{
		int id;
		String email;
		String first_name;
		String last_name;
		String avatar;

	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder(toBuilder = true)
	@Data

	public static class Support
	{
		
		String url;
		String sText;

	}
}

