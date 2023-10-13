package com.nussknacker.cinemareservation.reservation;

import com.nussknacker.cinemareservation.seatReservatiion.SeatReservation;
import com.nussknacker.cinemareservation.screening.Screening;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime activeTill;
    private Boolean paid;
    private String name;
    private String surname;
    @Column(name = "type")
    private TICKET_TYPE ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    @ToString.Exclude
    private Screening screening;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<SeatReservation> seatReservations;


}
