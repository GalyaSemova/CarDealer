package com.example.mobilele.service;

import com.example.mobilele.model.dto.ReCaptchaResponseDTO;

import java.util.Optional;

public interface ReCaptchaService {

    Optional<ReCaptchaResponseDTO> verify(String token);
}
