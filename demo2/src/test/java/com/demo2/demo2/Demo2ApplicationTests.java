package com.demo2.demo2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo2.demo2.model.user.UserAddressRequest;
import com.demo2.demo2.model.user.UserProfileRequest;
import com.demo2.demo2.model.user.UserRequest;
import com.demo2.demo2.model.user.UserResponse;
import com.demo2.demo2.service.user.UserService;

import org.apache.tomcat.jni.User;
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
		String usercode = "013";
		String userName = "sittichai2233";
		String password = "P@ssw0rdMFEC";
		String isEnabled = "Y";
		String isLocked = "N";
		Date expiredDate = new Date();
		String deleteFlag = "N";
		Integer createdBy = 1;
		Date createdAt = new Date();
	}
	interface UserProfileDataTest {
		String fisrtNameTh = "สิทธิชัย";
		String lastNameTh = "ธรรมวัตร";
		String fisrtNameEn = "Sittichai";
		String lastNameEn = "Thammawat";
		String mobileNumber = "0979969782";
		Date birthDate = new Date();
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

	// @Order(2)
	// @Test
	// void testCreateUserProfile() {
	// 	UserRequest userRequest = new UserRequest();
	// 	userRequest.setUserCode(UserDataTest.usercode);
	// 	userRequest.setUserName(UserDataTest.userName);
	// 	userRequest.setPassword(UserDataTest.password);
	// 	userRequest.setIsEnabled(UserDataTest.isEnabled);
	// 	userRequest.setIsLocked(UserDataTest.isLocked);
	// 	userRequest.setExpiredDate(UserDataTest.expiredDate);
	// 	userRequest.setCreatedBy(UserDataTest.createdBy);
	// 	userRequest.setCreatedAt(UserDataTest.createdAt);
	// 	userRequest.setDeleteFlag(UserDataTest.deleteFlag);

	// 	UserProfileRequest profile = new UserProfileRequest();
	// 	profile.setFirstNameTh(UserProfileDataTest.fisrtNameTh);
	// 	profile.setLastNameTh(UserProfileDataTest.lastNameTh);
	// 	profile.setFirstNameEn(UserProfileDataTest.fisrtNameEn);
	// 	profile.setFirstNameEn(UserProfileDataTest.lastNameEn);
	// 	profile.setMobileNumber(UserProfileDataTest.mobileNumber);
	// 	profile.setBirthDate(UserProfileDataTest.birthDate);
		
	// 	userRequest.setProfile(profile);
	// 	List<UserAddressRequest> addresses = new ArrayList<UserAddressRequest>();
	// 	UserAddressRequest addressRequest = new UserAddressRequest();
	// 	addressRequest.setLine1("124/6");
	// 	addressRequest.setLine2("romklo");
	// 	addressRequest.setPostCode("10520");
	// 	addressRequest.setType("Home");
	// 	addressRequest.setPrefer("1");
	// 	addressRequest.setCreatedBy(UserDataTest.createdBy);
	// 	addressRequest.setCreatedAt(UserDataTest.createdAt);
	// 	addressRequest.setDeleteFlag(UserDataTest.deleteFlag);
	// 	addresses.add(addressRequest);
	// 	addressRequest.setLine1("999");
	// 	addressRequest.setLine2("bangplee");
	// 	addressRequest.setPostCode("10540");
	// 	addressRequest.setType("Airport");
	// 	addressRequest.setPrefer("2");
	// 	addressRequest.setCreatedBy(UserDataTest.createdBy);
	// 	addressRequest.setCreatedAt(UserDataTest.createdAt);
	// 	addressRequest.setDeleteFlag(UserDataTest.deleteFlag);
	// 	addresses.add(addressRequest);

	// 	UserResponse userResponse = userService.createUser(userRequest);
	// 	Assertions.assertNotNull(userResponse);
	// 	Assertions.assertNotNull(userResponse.getId());
	// 	Assertions.assertEquals(UserDataTest.usercode, userRequest.getUserCode());
	// 	Assertions.assertEquals(UserDataTest.expiredDate, userRequest.getExpiredDate());
	// }

}
