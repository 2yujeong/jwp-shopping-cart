package woowacourse.shoppingcart.domain;

import java.util.regex.Pattern;

public class Customer {

    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 30;
    private static final String PHONE_REGEX = "^010-\\d{4}-\\d{4}$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";

    private final Long id;
    private final String email;
    private final String password;
    private final String name;
    private final String phone;
    private final String address;

    public Customer(Long id, String email, String password, String name, String phone, String address) {
        validate(password, name, phone);
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    private void validate(String password, String name, String phone) {
        validateNameLength(name);
        validatePhoneFormat(phone);
        validatePasswordFormat(password);
    }

    public Customer(String email, String password, String name, String phone, String address) {
        this(null, email, password, name, phone, address);
    }

    private void validateNameLength(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 1자 이상, 30자 이하여야 합니다.");
        }
    }

    private void validatePhoneFormat(String phone) {
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new IllegalArgumentException("전화번호의 형식이 맞지 않습니다.");
        }
    }

    private void validatePasswordFormat(String password) {
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new IllegalArgumentException("비밀번호의 형식이 맞지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
