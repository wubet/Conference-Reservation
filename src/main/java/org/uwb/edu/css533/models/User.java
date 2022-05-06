package org.uwb.edu.css533.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "user_id")
@Entity(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @NotEmpty
    private String first_name;
    @NotEmpty
    private String last_name;

    @NotNull
    @Email
    @Size(max = 50)
    @Column(unique = true)
    private String email_id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    Date updateDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDateTime;

    @JsonIgnoreProperties("user, room")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reservation> reservations;

    public User(){

    }
}
