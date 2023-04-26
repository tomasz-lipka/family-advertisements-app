package pl.familyadvertisementsapp.controller.base;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import pl.familyadvertisementsapp.model.dto.CustomUserDto;

/**
 * Abstract class providing a template for the registration controller implementation.
 *
 * @author Tomasz Lipka
 */
public abstract class RegistrationAbstractController<T> {

    @PostMapping()
    public abstract T createCustomUser(CustomUserDto customUserDto, BindingResult result);
}

