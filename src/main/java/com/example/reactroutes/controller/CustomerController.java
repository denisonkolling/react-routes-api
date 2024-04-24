package com.example.reactroutes.controller;

import com.example.reactroutes.dto.CustomerUpdateRequest;
import com.example.reactroutes.jwt.JWTUtil;
import com.example.reactroutes.model.Customer;
import com.example.reactroutes.model.CustomerRegistrationRequest;
import com.example.reactroutes.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTUtil jwtUtil;

    public CustomerController(CustomerService customerService,
                              JWTUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.selectAllCustomers();
    }

    @GetMapping("{customerId}")
    public Optional<Customer> getCustomer(
            @PathVariable("customerId") Integer customerId) {
        return customerService.selectCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity<?> registerCustomer(
            @RequestBody CustomerRegistrationRequest request) {
        customerService.insertCustomer(request);
        String jwtToken = jwtUtil.issueToken(request.email(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") Integer customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestBody CustomerUpdateRequest updateRequest) {
        customerService.updateCustomer(customerId, updateRequest);
    }

//    @PostMapping(
//            value = "{customerId}/profile-image",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void uploadCustomerProfilePicture(@PathVariable("customerId") Integer customerId,
//                                             @RequestParam("file") MultipartFile file) {
//        customerService.uploadCustomerProfileImage(customerId, file);
//    }
//
//    @GetMapping(
//            value = "{customerId}/profile-image",
//            produces = MediaType.IMAGE_JPEG_VALUE
//    )
//    public byte[] getCustomerProfileImage(
//            @PathVariable("customerId") Integer customerId) {
//        return customerService.getCustomerProfileImage(customerId);
//    }

}
