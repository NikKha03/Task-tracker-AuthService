package sharpBubbles.authService.models;

public class UserBuilder {

    private final User user;

    public UserBuilder() {
        this.user = new User();
    }

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder setTg(String tg) {
        user.setTg(tg);
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserBuilder setRole(UserRole role) {
        user.setRoles(role);
        return this;
    }

    public UserBuilder setRegistrationSource(RegistrationSource registrationSource) {
        user.setRegistrationSource(registrationSource);
        return this;
    }

    public User build() {
        return user;
    }

}
