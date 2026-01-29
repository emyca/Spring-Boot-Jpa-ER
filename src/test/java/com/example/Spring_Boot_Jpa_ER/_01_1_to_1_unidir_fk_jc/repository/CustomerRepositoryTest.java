package com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.repository;

import com.example.Spring_Boot_Jpa_ER._01_1_to_1_unidir_fk_jc.entity.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Customer id should be greater than zero when Customer saved.")
    public void shouldCustomerIdBeGreaterThanZero_whenCustomerSaved() {
        Customer customer = Customer.builder()
                .firstName("Alice")
                .lastName("Sunny")
                .email("alice@mail.com")
                .build();
        customerRepository.saveAndFlush(customer);
        assertThat(customer.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Customer id should be equal to expected when Customer found by its id.")
    public void shouldCustomerIdBeEqualToExpected_whenCustomerFoundById() {
        Customer customer = customerRepository.findById(1L).get();
        assertThat(customer.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("Should get filled Customers' list.")
    public void shouldGetFilledCustomersList() {
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    @DisplayName("Customer email should be equal to expected when Customer updated by its id.")
    public void shouldCustomerEmailBeEqualToExpected_whenCustomerUpdatedById() {
        Customer customer = customerRepository.findById(1L).get();
        customer.setEmail("sunny@mail.com");
        Customer customerUpdated = customerRepository.save(customer);
        assertThat(customerUpdated.getEmail()).isEqualTo("sunny@mail.com");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    @DisplayName("Customer should be null when Customer deleted by its id.")
    public void shouldCustomerBeNull_whenCustomerDeletedById() {
        customerRepository.deleteById(1L);
        Customer customer = null;
        Optional<Customer> optionalCustomer = customerRepository.findById(1L);
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }
        assertThat(customer).isNull();
    }
}