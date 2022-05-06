package org.uwb.edu.css533.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "room_id")
@Entity(name = "rooms")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;
    @NotNull
    @Column(unique = true)
    private Integer room_number;
    @NotEmpty
    @Column(unique = true)
    private String room_name;
    @NotNull
    private Integer room_capacity;
    @NotEmpty
    private String room_location;
    @NotEmpty
    private String room_type;
    @NotEmpty
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    Date updateDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDateTime;

//    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    @JsonIgnoreProperties("room, user")
    private List<Reservation> reservations;

    public Room() {

    }
}
