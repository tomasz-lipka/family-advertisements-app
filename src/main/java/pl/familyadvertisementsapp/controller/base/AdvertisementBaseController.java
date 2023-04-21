package pl.familyadvertisementsapp.controller.base;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import pl.familyadvertisementsapp.model.Advertisement;

public abstract class AdvertisementBaseController<T> {

    @GetMapping("/all")
    protected abstract T getAll();

    @GetMapping("/my")
    protected abstract T getMy();

    @PostMapping()
    protected abstract T create(Advertisement advertisement, BindingResult result);

    @DeleteMapping("/{id}")
    protected abstract T delete(Long id);

    @PutMapping()
    protected abstract T update(Advertisement advertisement, BindingResult result);
}
