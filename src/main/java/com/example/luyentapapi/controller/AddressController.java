package com.example.luyentapapi.controller;

import com.example.luyentapapi.model.Address;
import com.example.luyentapapi.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @GetMapping
    public ResponseEntity<Iterable<Address>> findAll() {
        Iterable<Address> addressIterable = addressService.findAll();
        return new ResponseEntity<>(addressIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        Optional<Address> addressOptional = addressService.findById(id);
        return new ResponseEntity<>(addressOptional.get(), HttpStatus.OK);
    }
@PostMapping
public ResponseEntity<Address> save(@RequestBody Address address){
        addressService.save(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
}
@PutMapping("/{id}")
    public ResponseEntity<Address>edit(@PathVariable Long id,@RequestBody Address address){
        Optional<Address>addressOptional=addressService.findById(id);
        if(!addressOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        address.setId(addressOptional.get().getId());
        addressService.save(address);
        return new ResponseEntity<>(HttpStatus.OK);
}
@DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Long id){
      Optional<Address>addressOptional=addressService.findById(id);
      if(!addressOptional.isPresent()){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      addressService.delete(id);
        return new ResponseEntity<>(addressOptional.get(),HttpStatus.NO_CONTENT);
}

}
