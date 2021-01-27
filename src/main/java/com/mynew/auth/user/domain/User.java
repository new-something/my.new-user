package com.mynew.auth.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
@Table(name = "user")
@Builder
@EqualsAndHashCode(of = "userId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    public static final User EMPTY = new User();

    @Id
    private Long userId;

    @Id
    @Enumerated(EnumType.STRING)
    private ProviderType type;

    private String userName;

    private String email;

    private String name;


}
