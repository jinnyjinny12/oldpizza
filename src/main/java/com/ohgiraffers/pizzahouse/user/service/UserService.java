package com.ohgiraffers.pizzahouse.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohgiraffers.pizzahouse.user.model.UserDTO;
import com.ohgiraffers.pizzahouse.user.model.UserEntity;
import com.ohgiraffers.pizzahouse.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	// findbyUserName : 이름을 찾고
	// 그 이름 == 입력한 이름 사람을 가져와라
	// 그 같은 사람들 중에 주소(우편번호, 기본주소, 상세주소)가 같은 사람이 있으면
	// 중복회원입니다 라고 말해라 저장하지 말고.
	// repository 와 service 메서드의 반환타입이 같아야 한다.

	@Transactional
	public UserDTO userSave(UserDTO userDTO) {
		List<UserEntity> users = userRepository.findByUserName(userDTO.getUserName());
		if (!users.isEmpty()) {
			String targetAddress = userDTO.getAddress() + userDTO.getAddressDetail() + userDTO.getPostCode();
			List<UserEntity> allUser = userRepository.findAll();
			for (UserEntity user : allUser) {
				String value = user.getAddress() + user.getAddressDetail() + user.getPostCode();
				if (targetAddress.equals(value)) {
					return null;
				}
			}
		}

		UserEntity userEntity = new UserEntity.Builder()
			.setUserName(userDTO.getUserName())
			.setUserAge(userDTO.getUserAge())
			.setPostCode(userDTO.getPostCode())
			.setAdderess(userDTO.getAddress())
			.setAddressDetail(userDTO.getAddressDetail())
			.builder();

		userRepository.save(userEntity);

		return userDTO;

	}

	public List<UserDTO> getAllUser() {
		List<UserEntity> users = userRepository.findAll();
		List<UserDTO> userDTOS = new ArrayList<>();

		if (users.size() > 0) {
			for (UserEntity user : users) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserName(user.getUserName());
				userDTO.setUserAge(user.getUserAge());
				userDTO.setPostCode(user.getPostCode());
				userDTO.setAddress(user.getAddress());
				userDTO.setAddressDetail(user.getAddressDetail());
				userDTOS.add(userDTO);
			}
		} else {
			return null;
		}
		return userDTOS;
	}

	public UserDTO detailUser(Integer userId) {
		Optional<UserEntity> user = userRepository.findById(userId);

		if (user.isPresent()) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName(user.get().getUserName());
			userDTO.setUserAge(user.get().getUserAge());
			userDTO.setPostCode(user.get().getPostCode());
			userDTO.setAddress(user.get().getAddress());
			userDTO.setAddressDetail(user.get().getAddressDetail());

			return userDTO;
		} else {
			return null;
		}
	}

	@Transactional
	public UserDTO updateUser(Integer userId, UserDTO userDTO) {
		Optional<UserEntity> user = userRepository.findById(userId);

		if (user.isPresent()) {
			UserEntity existingUser = user.get();
			existingUser.setUserName(userDTO.getUserName());
			existingUser.setUserAge(userDTO.getUserAge());
			existingUser.setPostCode(userDTO.getPostCode());
			existingUser.setAddress(userDTO.getAddress());
			existingUser.setAddressDetail(userDTO.getAddressDetail());

			userRepository.save(existingUser);

			return convertToDTO(existingUser);
		} else {
			throw new RuntimeException("유저의 아이디를 찾을 수 없습니다." + userId);
		}
	}

	private UserDTO convertToDTO(UserEntity userEntity) {
		return new UserDTO(
			userEntity.getUserId(),
			userEntity.getUserName(),
			userEntity.getUserAge(),
			userEntity.getPostCode(),
			userEntity.getAddress(),
			userEntity.getAddressDetail()
		);
	}
}
