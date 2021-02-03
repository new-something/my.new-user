package com.mynew.auth.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "user", indexes = @Index(name = "provider_id_index", columnList = "providerId", unique = true))
@Builder
@EqualsAndHashCode(of = "id", callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

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
