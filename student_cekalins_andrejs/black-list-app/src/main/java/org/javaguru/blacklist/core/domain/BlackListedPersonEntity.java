package org.javaguru.blacklist.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "black_listed_persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlackListedPersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_first_name", nullable = false)
    private String firstName;

    @Column(name = "person_last_name", nullable = false)
    private String lastName;

    @Column(name = "person_code", nullable = false)
    private String personCode;

}
