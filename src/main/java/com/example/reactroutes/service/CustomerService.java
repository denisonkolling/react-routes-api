package com.example.reactroutes.service;

import com.example.reactroutes.dto.CustomerUpdateRequest;
import com.example.reactroutes.model.Customer;
import com.example.reactroutes.model.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer customerId);
    void insertCustomer(CustomerRegistrationRequest customer);
    boolean existsCustomerWithEmail(String email);
    boolean existsCustomerById(Integer customerId);
    void deleteCustomerById(Integer customerId);
    void updateCustomer(Integer customerId, CustomerUpdateRequest update);
    void updateCustomer(Customer update);
    Customer selectUserByEmail(String email);
    void updateCustomerProfileImageId(String profileImageId, Integer customerId);
//    void uploadCustomerProfileImage(Integer customerId, MultipartFile file);
//    byte[] getCustomerProfileImage(Integer customerId);
}
