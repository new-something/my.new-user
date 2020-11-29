package com.mynew.auth.user.service.dto.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mynew.auth.user.domain.User;
import com.mynew.auth.user.service.dto.Persistent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class GithubUser implements Persistent {
    public static final GithubUser NONE = new GithubUser();

    private String login;
    private Long id;
    private String nodeId;
    private String avatarUrl;
    @Deprecated
    private String gravatarId; // https://developer.github.com/changes/2014-09-05-removing-gravatar-id/
    private String url; // 특정 user 의 정보를 가져오는 api url https://api.github.com/users/{username}
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type; // User
    private boolean siteAdmin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private boolean hireable;
    private String bio;
    private String twitterUsername;
    private Integer publicRepos;
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer privateGists;
    private Integer totalPrivateRepos;
    private Integer ownedPrivateRepos;
    private Long diskUsage;
    private Integer collaborators;
    private boolean twoFactorAuthentication;
    private Plan plan;

    @Override
    public User toUser() {
        return User.builder()
                .id(id)
                .userName(login)
                .email(email)
                .name(name)
                .build();
    }
}
