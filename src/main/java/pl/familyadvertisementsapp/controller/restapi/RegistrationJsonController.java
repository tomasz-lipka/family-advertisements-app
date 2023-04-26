package pl.familyadvertisementsapp.controller.restapi;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.familyadvertisementsapp.controller.base.RegistrationAbstractController;
import pl.familyadvertisementsapp.exception.CustomUserServiceException;
import pl.familyadvertisementsapp.model.dto.CustomUserDto;
import pl.familyadvertisementsapp.service.customuser.CustomUserService;

/**
 * Rest controller for the Custom User entity.
 *
 * @author Tomasz Lipka
 */
@RestController
@RequestMapping("/restapi/registration")
@AllArgsConstructor
public class RegistrationJsonController extends RegistrationAbstractController {

    private CustomUserService customUserService;

    @Override
    public ResponseEntity<String> createCustomUser(@RequestBody @Valid CustomUserDto customUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.toString());
        }
        try {
            customUserService.createUserWithEncodedPassword(customUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CustomUserServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
