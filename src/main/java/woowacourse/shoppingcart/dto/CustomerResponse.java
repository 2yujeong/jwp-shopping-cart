package woowacourse.shoppingcart.dto;

import woowacourse.shoppingcart.domain.Customer;

public class CustomerResponse {

    private final Long id;

    private final String email;

    private final String name;
    private final String phone;
    private final String address;
    public CustomerResponse(Long id, String email, String name, String phone, String address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public static CustomerResponse of(Long id, Customer customer) {
        return new CustomerResponse(
                id, customer.getEmail(), customer.getName(), customer.getPhone(), customer.getAddress());
    }

    public static CustomerResponse of(Customer customer) {
        return new CustomerResponse(
                customer.getId(), customer.getEmail(), customer.getName(), customer.getPhone(), customer.getAddress());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
