package com.eventu.event_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "feed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    private Long authorId;
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", columnDefinition = "varchar(30)")
    private PostType postType;

    private LocalDate createdAt;



}
