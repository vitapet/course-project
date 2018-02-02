package com.gmail.vitaliapetsenak.shop.web.validator;

import com.gmail.vitaliapetsenak.shop.web.model.FormDataNews;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class NewsValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FormDataNews.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "news.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "news.description.empty");
        ValidationUtils.rejectIfEmpty(errors, "author", "news.author.empty");

        FormDataNews news = (FormDataNews) target;

        if (news.getId() == null && news.getFile() != null && news.getFile().isEmpty()) {
            errors.rejectValue("file", "news.file.empty");
        }
        Pattern pattern;

        if (!news.getName().isEmpty() && news.getName() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9.\\s]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(news.getName()).matches())) {
                errors.rejectValue("name", "news.name.invalid");
            }
        }
        if (!news.getDescription().isEmpty() && news.getDescription() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9.\\s]{1,1000}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(news.getDescription()).matches())) {
                errors.rejectValue("description", "news.description.invalid");
            }
        }
        if (!news.getAuthor().isEmpty() && news.getAuthor() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9\\s]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(news.getAuthor()).matches())) {
                errors.rejectValue("author", "news.author.invalid");
            }
        }
    }
}
