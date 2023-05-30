package com.in28minutes.rest.webservices.restfulwebservices.social.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    //    @JsonProperty("user_name")
    @Size(min = 2, max = 30, message = "Name should be between 2 to 30 characters long.")
    @NotNull(message = "Name cannot be null.")
    private String name;

    //    @JsonProperty("birth_date")
    @Past(message = "Birth date should be in the past.")
    @NotNull(message = "Birth date cannot be null.")
    private LocalDateTime birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
