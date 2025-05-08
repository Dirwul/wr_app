package com.example.wrservice.core;

import com.example.wrservice.core.utils.dto.DTOMapper;
import com.example.wrservice.core.utils.dto.UserRequest;
import com.example.wrservice.core.utils.dto.UserResponse;
import com.example.wrservice.core.utils.exception.UserAlreadyExistsException;
import com.example.wrservice.core.utils.exception.UserNotFoundException;
import com.example.wrservice.data.model.User;
import com.example.wrservice.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final DTOMapper mapper;

	@Autowired
	public UserService(UserRepository userRepository, DTOMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	public UserResponse createUser(UserRequest userRequest) {
		if (userRepository.existsByEmail(userRequest.email())) {
			throw new UserAlreadyExistsException("Пользователь с таким email уже существует");
		}

		// dto -> entity -> save
		User user = userRepository.save(mapper.toEntity(userRequest));

		// return response
		log.info("Пользователь сохранен");
		return mapper.toResponse(user);
	}

	@Transactional
	public UserResponse getUser(long id) {
		User user = findUserOrThrowException(id);
		return mapper.toResponse(user);
	}

	@Transactional
	public UserResponse updateUser(long id, UserRequest userRequest) {
		User user = findUserOrThrowException(id);
		user.setUsername(userRequest.username());
		user.setEmail(userRequest.email());
		userRepository.save(user);

		log.info("Пользователь c id {} обновлен", id);
		return mapper.toResponse(user);
	}

	public void deleteUser(long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("Пользователя с таким id не существует");
		}
		userRepository.deleteById(id);
		log.info("Пользовател удален");
	}

	public User findUserOrThrowException(long id) throws UserNotFoundException {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			log.info("Пользователь c id {} найден", id);
			return optUser.get();
		}
		throw new UserNotFoundException("Пользователя с таким id не существует");
	}
}
