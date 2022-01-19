package com.example.demo.model;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min = 3, max = 50)
    private String name;


    @NotBlank
    @Size(min = 3, max = 50)
    private String username;


    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;



    @NotBlank
    @Size(min = 6, max = 100)
    private String password;


    @Lob
    private String avatar;

    private LocalDate dob;

    private String phone;

    private String address;

    private Double amount;

    private Long point;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus status;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<FoodOrder> foodOrderList;

    @OneToMany
    private List<Message> messageList;

    private Long restaurantId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();


    public User() {
    }


    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name, String username, String email, String password, String avatar) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public User(Long id, String name, String username, String email, String password, String avatar, LocalDate dob, String phone, String address, Double amount, Long point, GeneralStatus status, List<FoodOrder> foodOrderList, List<Message> messageList, Long restaurantId, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.amount = amount;
        this.point = point;
        this.status = status;
        this.foodOrderList = foodOrderList;
        this.messageList = messageList;
        this.restaurantId = restaurantId;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public List<FoodOrder> getFoodOrderList() {
        return foodOrderList;
    }

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
