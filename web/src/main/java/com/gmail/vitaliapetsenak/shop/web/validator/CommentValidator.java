package com.gmail.vitaliapetsenak.shop.web.validator;

import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "text", "comment.text.empty");
        CommentDTO comment = (CommentDTO) target;

        if (!comment.getText().isEmpty() && comment.getText() != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9.\\s]{1,250}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(comment.getText()).matches())) {
                errors.rejectValue("text", "text.");
            }
        }
    }
}
