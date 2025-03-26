package com.algebra.parcijalni_ispit.Auth;

public interface RefreshTokenService {

    RefreshToken findByUserId(Long userId);

    RefreshToken generateRefreshToken(Long userId);

    RefreshToken findByToken (String token);
}