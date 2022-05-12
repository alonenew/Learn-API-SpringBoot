package com.demo2.demo2;

import java.util.Date;

import com.demo2.demo2.model.user.UserRequest;
import com.demo2.demo2.model.user.UserResponse;
import com.demo2.demo2.service.user.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Demo2ApplicationTests {

	@Autowired
	private UserService userService;

	interface UserDataTest {
		String usercode = "011";
		String userName = "sittichai";
		String password = "P@ssw0rdMFEC";
		String isEnabled = "Y";
		String isLocked = "N";
		Date expiredDate = new Date();
		String deleteFlag = "N";
		Integer createdBy = 1;
		Date createdAt = new Date();

	}

	@Order(1)
	@Test
	void testCreateUser() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUserCode(UserDataTest.usercode);
		userRequest.setUserName(UserDataTest.userName);
		userRequest.setPassword(UserDataTest.password);
		userRequest.setIsEnabled(UserDataTest.isEnabled);
		userRequest.setIsLocked(UserDataTest.isLocked);
		userRequest.setExpiredDate(UserDataTest.expiredDate);
		userRequest.setCreatedBy(UserDataTest.createdBy);
		userRequest.setCreatedAt(UserDataTest.createdAt);
		userRequest.setDeleteFlag(UserDataTest.deleteFlag);
		UserResponse userResponse = userService.createUser(userRequest);

		Assertions.assertNotNull(userResponse);
		Assertions.assertNotNull(userResponse.getId());
		Assertions.assertEquals(UserDataTest.usercode, userRequest.getUserCode());
		Assertions.assertEquals(UserDataTest.expiredDate, userRequest.getExpiredDate());
	
	}

}
