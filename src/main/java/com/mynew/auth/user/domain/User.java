package com.mynew.auth.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "user", indexes = @Index(name = "provider_id_index", columnList = "providerId"))
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    public static final User EMPTY = new User();

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String providerId;

    private String userName;

    private String email;

    private String name;


}
