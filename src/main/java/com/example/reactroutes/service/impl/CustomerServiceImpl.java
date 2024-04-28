package com.example.reactroutes.service.impl;

import com.example.reactroutes.dto.CustomerDTOMapper;
import com.example.reactroutes.dto.CustomerUpdateRequest;
import com.example.reactroutes.exception.DuplicateResourceException;
import com.example.reactroutes.exception.RequestValidationException;
import com.example.reactroutes.exception.ResourceNotFoundException;
import com.example.reactroutes.model.Customer;
import com.example.reactroutes.model.CustomerRegistrationRequest;
import com.example.reactroutes.repository.CustomerRepository;
import com.example.reactroutes.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerDTOMapper = customerDTOMapper;
    }


    @Override
    public List<Customer> selectAllCustomers() {
        Page<Customer> page = customerRepository.findAll(Pageable.ofSize(1000));
        return page.getContent();
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public void insertCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                passwordEncoder.encode(customerRegistrationRequest.password()));

        customerRepository.save(customer);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public boolean existsCustomerById(Integer id) {
        return customerRepository.existsCustomerById(id);
    }

    @Override
    public void deleteCustomerById(Integer customerId) {

        checkIfCustomerExistsOrThrow(customerId);

        customerRepository.deleteById(customerId);
    }


    @Override
    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer with id [%s] not found".formatted(customerId)
                ));

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerRepository.existsCustomerByEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }
        customerRepository.save(customer);

    }

    @Override
    public void updateCustomer(Customer update) {
        customerRepository.save(update);
    }

    @Override
    public Customer selectUserByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public void updateCustomerProfileImageId(String profileImageId,
                                             Integer customerId) {
        customerRepository.updateProfileImageId(profileImageId, customerId);
    }

//    @Override
//    public void uploadCustomerProfileImage(Integer customerId, MultipartFile file) {
//
//        checkIfCustomerExistsOrThrow(customerId);
//        String profileImageId = UUID.randomUUID().toString();
//
//        try {
//            s3Service.putObject(s3Buckets.getCustomer(),
//                    "profile-images/%s/%s".formatted(customerId, profileImageId),
//                    file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException("failed to upload profile image", e);
//        }
//
//        customerRepository.updateProfileImageId(profileImageId, customerId);
//
//    }

//    @Override
//    public byte[] getCustomerProfileImage(Integer customerId) {
//
//        var customer = customerRepository.findById(customerId)
//                .map(customerDTOMapper)
//                .orElseThrow(() -> new ResourceNotFoundException("customer with id [%s] profile image not found".formatted(customerId)));
//
//        if (customer.profileImageId().isBlank()) {
//            throw new ResourceNotFoundException("customer with id [%s] not found".formatted(customerId));
//        }
//
//        byte[] profileImage = s3Service.getObject(
//                s3Buckets.getCustomer(),
//                "profile-images/%s/%s".formatted(customerId, customer.profileImageId())
//        );
//        return profileImage;
//    }
//
    private void checkIfCustomerExistsOrThrow(Integer customerId) {
        if (!customerRepository.existsCustomerById(customerId)) {
            throw new ResourceNotFoundException(
                    "customer with id [%s] not found".formatted(customerId)
            );

        }
    }
}
