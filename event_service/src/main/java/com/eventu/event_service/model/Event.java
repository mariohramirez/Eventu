package com.eventu.event_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventNumber;
    private String title;
    private String description;
    private String shortDescription;
    private LocalDate starTime;
    private LocalDate endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    private Long organizerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(30)")
    private StatusName status;
    @Enumerated(EnumType.STRING)
    @Column(name = "visibility", columnDefinition = "varchar(30)")
    private VisibilityName visibility;
    private int maxAttendees;
    private int minAttendees;
    private int currentAttendees;
    private LocalDate createdAt;
    private LocalDate publishedAt;
    private LocalDate updatedAt;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventCategories> categories = new HashSet<>();
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Attendees> attendees = new HashSet<>();
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventTags> eventTags = new HashSet<>();
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Feed> feedItems = new HashSet<>();


}
