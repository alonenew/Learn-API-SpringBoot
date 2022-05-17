package com.demo2.demo2;

import java.util.*;

import com.demo2.demo2.model.user.*;
import com.demo2.demo2.service.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Demo2ApplicationTests {

	@Autowired
	private UserService userService;

	interface UserDataTest {
		String usercode = "025";
		String userName = "patamawadeeK";
		String password = "P@ssw0rdMFEC";
		Integer user_id = 25;
		String fisrtNameTh = "สิทธิชัย";
		String lastNameTh = "ธรรมวัตร";
		String fisrtNameEn = "Sittichai";
		String lastNameEn = "Thammawat";
		String mobileNumber = "0979969782";
		Date birthDate = new Date();
	}


	@Order(1)
	@Test
	void testCreateUser() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUserCode(UserDataTest.usercode);
		userRequest.setUserName(UserDataTest.userName);
		userRequest.setPassword(UserDataTest.password);
		UserResponse userResponse = userService.createUser(userRequest);

		Assertions.assertNotNull(userResponse);
		Assertions.assertNotNull(userResponse.getId());
		Assertions.assertEquals(UserDataTest.usercode, userRequest.getUserCode());
	}

	@Order(2)
	@Test
	void testCreateUserProfile() {

		UserRequest userRequest = new UserRequest();
		userRequest.setUserCode(UserDataTest.usercode);
		userRequest.setUserName(UserDataTest.userName);
		userRequest.setPassword(UserDataTest.password);

		UserProfileRequest profile = new UserProfileRequest();
		profile.setFirstNameTh(UserDataTest.fisrtNameTh);
		profile.setLastNameTh(UserDataTest.lastNameTh);
		profile.setFirstNameEn(UserDataTest.fisrtNameEn);
		profile.setLastNameEn(UserDataTest.lastNameEn);
		profile.setMobileNumber(UserDataTest.mobileNumber);
		profile.setBirthDate(UserDataTest.birthDate);
		UserProfileResponse userProfileResponse = userService.createUserProfile(profile, UserDataTest.user_id);

		Assertions.assertNotNull(userProfileResponse);
		Assertions.assertNotNull(userProfileResponse.getId());
		Assertions.assertNotNull(userProfileResponse.getUser());
	}

	
	@Order(3)
	@Test
	void testCreateUserAddress() {

		UserRequest userRequest = new UserRequest();
		userRequest.setUserCode(UserDataTest.usercode);
		userRequest.setUserName(UserDataTest.userName);
		userRequest.setPassword(UserDataTest.password);

		UserAddressRequest addressRequest = new UserAddressRequest();
		addressRequest.setLine1("349 SJ Infinite");
		addressRequest.setLine2("Chatujak");
		addressRequest.setPostCode("10900");
		addressRequest.setType("Work");
		addressRequest.setPrefer("Y");
		UserAddressResponse userAddressResponse = userService.createUserAddress(addressRequest, UserDataTest.user_id);

		Assertions.assertNotNull(userAddressResponse);
		Assertions.assertNotNull(userAddressResponse.getId());
		Assertions.assertNotNull(userAddressResponse.getUser());
	}

	@Order(4)
	@Test
	void testCreateUserAndProfile() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUserCode(UserDataTest.usercode);
		userRequest.setUserName(UserDataTest.userName);
		userRequest.setPassword(UserDataTest.password);


		UserProfileRequest profile = new UserProfileRequest();
		profile.setFirstNameTh(UserDataTest.fisrtNameTh);
		profile.setLastNameTh(UserDataTest.lastNameTh);
		profile.setFirstNameEn(UserDataTest.fisrtNameEn);
		profile.setLastNameEn(UserDataTest.lastNameEn);
		profile.setMobileNumber(UserDataTest.mobileNumber);
		profile.setBirthDate(UserDataTest.birthDate);
		userRequest.setProfile(profile);

		List<UserAddressRequest> addreList = new ArrayList<UserAddressRequest>();
		UserAddressRequest addressRequest = new UserAddressRequest();
		addressRequest.setLine1("124/6 m.3");
		addressRequest.setLine2("ladkrabang");
		addressRequest.setPostCode("10520");
		addressRequest.setType("Home");
		addressRequest.setPrefer("Y");
		addreList.add(addressRequest);

		addressRequest = new UserAddressRequest();
		addressRequest.setLine1("999");
		addressRequest.setLine2("Bangplee");
		addressRequest.setPostCode("10540");
		addressRequest.setType("Airport");
		addressRequest.setPrefer("Y");
		addreList.add(addressRequest);

		userRequest.setAddresses(addreList);	

		UserResponse userResponse = userService.createUserAndProfile(userRequest);

		Assertions.assertNotNull(userResponse);
		Assertions.assertNotNull(userResponse.getId());
	}
}
