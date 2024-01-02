package harakiri.controller;


import harakiri.config.Constants;
import harakiri.dto.request.AuthorizeRequest;
import harakiri.dto.request.DeleteAccountRequest;
import harakiri.dto.request.RefreshTokenRequest;
import harakiri.dto.request.RegisterRequest;
import harakiri.dto.response.TokenResponse;
import harakiri.exceptions.InvalidPasswordException;
import harakiri.mapper.AuthMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Auth")
@RequiredArgsConstructor
public class AuthController {
    final private AuthMapper authMapper;

    @PostMapping("/register")
    public TokenResponse register(@Valid @RequestBody RegisterRequest request) {
        return authMapper.register(request);
    }

    @PostMapping("/auth")
    public TokenResponse authorize(@Valid @RequestBody AuthorizeRequest request) throws InvalidPasswordException {
        return authMapper.authorize(request);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@Valid @RequestBody RefreshTokenRequest request,
                                 @RequestHeader(Constants.authHeaderName) String accessToken) throws InvalidPasswordException {
        return authMapper.refresh(request, accessToken);
    }

    @PostMapping("/signout")
    public void signout(@RequestHeader(Constants.authHeaderName) String token) {
        authMapper.signout(token);
    }

    @DeleteMapping("/account")
    public void deleteAccount(@RequestHeader(Constants.authHeaderName) String token,
                              @RequestBody DeleteAccountRequest request) throws InvalidPasswordException {
        authMapper.deleteAccount(token, request);
    }
}
