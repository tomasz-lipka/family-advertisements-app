package pl.familyadvertisementsapp.view.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest")
public class RestAdvertisementController {

    //TODO update changes to reflect thymleaf controller
//
//    private AdvertisementService advertisementService;
//
//    public RestAdvertisementController(AdvertisementService advertisementService) {
//        this.advertisementService = advertisementService;
//    }
//
//    @GetMapping("/advertisements")
//    public Collection<Advertisement> getAll() {
//        return advertisementService.getAll();
//    }
//
//    @GetMapping("/advertisements/{id}")
//    public Advertisement getById(@PathVariable Long id) {
//        return advertisementService.getById(id);
//    }
//
//    @PostMapping("/advertisements")
//    public Advertisement create(@RequestBody Advertisement advertisement) {
//        return advertisementService.create(advertisement);
//    }
//
//    @DeleteMapping("/advertisements/{id}")
//    public ResponseEntity<Long> delete(@PathVariable Long id) {
//        advertisementService.deleteById(id);
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }
//
//    @PutMapping("/advertisements")
//    public Advertisement update(@RequestBody Advertisement advertisement) {
//        return advertisementService.update(advertisement);
//    }
}
