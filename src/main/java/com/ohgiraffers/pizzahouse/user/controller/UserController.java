package com.ohgiraffers.pizzahouse.user.controller;

import com.ohgiraffers.pizzahouse.user.model.UserDTO;

import com.ohgiraffers.pizzahouse.user.service.UserService;
import com.ohgiraffers.pizzahouse.utill.Utills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDTO userDTO) {
		Map<String, Object> map = new HashMap<>();

		if (userDTO.getUserName() == null || !Utills.regex(userDTO.getUserName())) {
			map.put("error", "정확한 이름을 입력하세요. ex)강형석");
			return ResponseEntity.status(400).body(map);

		} else if (userDTO.getUserAge() < 20) {
			map.put("error", "20세 이상만 가입이 가능합니다");
			return ResponseEntity.status(400).body(map);

		} else if (String.valueOf(userDTO.getPostCode()).length() != 5) {
			map.put("error", "우편번호 5자리를 정확하게 입력하세요");
			return ResponseEntity.status(400).body(map);

		} else if (userDTO.getAddress() == null || userDTO.getAddress().trim().isEmpty()) {
			map.put("error", "기본주소는 필수 입력값입니다.");
			return ResponseEntity.status(400).body(map);

		} else if (userDTO.getAddressDetail() == null || userDTO.getAddressDetail().trim().isEmpty()) {
			map.put("error", "상세주소는 필수 입력값입니다.");
			return ResponseEntity.status(400).body(map);
		}

		userService.userSave(userDTO);
		map.put("success", "회원등록이 완료되었습니다");
		return ResponseEntity.status(201).body(map);
	}

	// 전체조회
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAllUsers() {
		Map<String, Object> map = new HashMap<>();

		List<UserDTO> userDTOList = userService.getAllUser();
		if (userDTOList != null && !userDTOList.isEmpty()) {
			map.put("userInfo", userDTOList);
			return ResponseEntity.ok(map);
		} else {
			map.put("error", "데이터가 없습니다.");
			return ResponseEntity.status(400).body(map);
		}
	}

	//상세조회
	@GetMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> detailUser(@PathVariable Integer userId) {
		Map<String, Object> map = new HashMap<>();

		if (userId == null || userId <= 0) {
			map.put("error", "다시 시도해주세요.");
			return ResponseEntity.status(400).body(map);
		}

		UserDTO findDTO = userService.detailUser(userId);
		if (findDTO != null) {
			map.put("UserInfo", findDTO);
			return ResponseEntity.status(200).body(map);
		} else {
			map.put("error", "조회실패");
			return ResponseEntity.status(400).body(map);
		}
	}

	@PutMapping("/delete/{userId}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer userId) {
		Map<String, Object> map = new HashMap<>();

		if (userId <= 0 || userId == null) {
			map.put("error", "다시 시도해주세요");
			return ResponseEntity.status(400).body(map);
		}
		UserDTO findDTO = userService.deleteUpdate(userId);
		if (findDTO != null) {
			map.put("userInfo", "성공적으로 삭제되었습니다!");
			return ResponseEntity.status(201).body(map);

		} else {
			map.put("error", "삭제할 데이터가 없습니다");
			return ResponseEntity.status(400).body(map);
		}

	}

}