package com.tddproject.register.service;

import com.tddproject.register.domain.User;
import com.tddproject.register.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully(){
		User userBeforeSave = new User(null, "Jacson", "12345678", LocalDate.of(1983, 4, 6));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository).save(userBeforeSave);
		// caso queira que seja verifica se passou pelo método mais de 1 vez
//		Mockito.verify(userRepository, Mockito.times(3)).save(userBeforeSave);

		Assertions.assertEquals(userAfterSave, user);
	}

	@ParameterizedTest
	@ValueSource(ints = {2011, 1960})
	public void should_return_error_when_age_is_invalid(Integer year){
		User user = new User(null, "Jacson", "12345678", LocalDate.of(year, 4, 20));

		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

//		Mockito.verify(userRepository, Mockito.never()).save(user);
		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("Idade não permitida", exception.getMessage());
	}
}
