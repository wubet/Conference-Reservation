package org.uwb.edu.css533.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "reservation_id")
@Entity(name="Reservations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservation_id;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-DD-YYYY HH:MM")
    @NotNull
    private Date meeting_start_time;
    @NotNull
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-DD-YYYY HH:MM")
    private Date meeting_end_time;
    @NotEmpty
    private String reservation_description;
    @NotEmpty
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    Date updateDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDateTime;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnoreProperties("reservation, user")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("reservation, room")
    private User user;

    public Reservation(){

    }
}
