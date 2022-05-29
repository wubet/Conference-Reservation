package org.uwb.edu.css533.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "room_id")
@Entity(name = "rooms")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;
    @NotNull
    private Integer roomNumber;
    @NotEmpty
    @Column(unique = true)
    private String roomName;
    @NotNull
    private Integer roomCapacity;
    @NotEmpty
    private String roomLocation;
    @NotEmpty
    private String roomType;
    @NotEmpty
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    Date updateDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;

    public Room() {

    }
}
