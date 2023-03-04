package com.user.userops.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.user.userops.model.ReqRes;
import com.user.userops.model.ReqRes.UserData;
import com.user.userops.model.User;
import com.user.userops.model.UserV2;

@Service
public class ReqResService {

	public static int id = 0;
	public static ArrayList<ReqRes> rlist = new ArrayList<ReqRes>();

	public ReqRes addUser(ReqRes reqres) {
		ReqRes.UserData userdata = ReqRes.UserData.builder()
				.first_name(reqres.getUserData().getFirst_name())
				.last_name(reqres.getUserData().getLast_name())
				.email(reqres.getUserData().getEmail())
				.avatar(reqres.getUserData().getAvatar()).build();
		userdata.setId(++id);
		
		ReqRes.Support supportdata = ReqRes.Support.builder()
				.url(reqres.getSupport().getUrl())
				.sText(reqres.getSupport().getSText())
				.build();

		ReqRes getUser= ReqRes.builder()
				.page(reqres.getPage())
				.perPage(reqres.getPerPage())
				.totalPage(reqres.getTotalPage())
				.total(reqres.getTotal())
				.userData(userdata)
				.support(supportdata)
				.build();
		rlist.add(getUser);
		
		return getUser;
				
	}

}
