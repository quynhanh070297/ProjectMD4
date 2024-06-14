package rikkei.projectmodule4.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rikkei.projectmodule4.repository.IUserRepository;

@Component
@RequiredArgsConstructor
public class HandleEmailExist implements ConstraintValidator<EmailExist,String> {
	private final IUserRepository userRepository;
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !userRepository.existsByEmail(s);
	}
}
