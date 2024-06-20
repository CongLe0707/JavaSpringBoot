package com.bookstore.validation;


import com.bookstore.entity.User;
import com.bookstore.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUserldValidator  implements ConstraintValidator<ValidUserId, User> {
    @Autowired
    private  IUserRepository userRepository;


    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository == null  || user == null) {
            return true; // Trả về true nếu id null
        }
        // Kiểm tra xem id đã tồn tại trong cơ sở dữ liệu chưa
        return userRepository.findByUserId(user.getId()) == null;
    }
}
