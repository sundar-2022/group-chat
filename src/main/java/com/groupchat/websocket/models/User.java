package com.groupchat.websocket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(max = 120)
    private String password;

}
