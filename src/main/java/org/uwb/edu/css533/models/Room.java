package org.uwb.edu.css533.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
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
    private Integer room_number;
    private String room_name;
    private Integer room_capacity;
    private String room_location;
    private String room_type;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    @JsonIgnoreProperties("room, user")
    private List<Reservation> reservations;

    public Room() {

    }
}
