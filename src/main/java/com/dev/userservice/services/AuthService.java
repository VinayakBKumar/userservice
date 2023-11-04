package com.dev.userservice.services;

import com.dev.userservice.dtos.*;
import com.dev.userservice.exceptions.AlreadyExistException;
import com.dev.userservice.exceptions.NotFoundException;
import com.dev.userservice.models.Session;
import com.dev.userservice.models.SessionStatus;
import com.dev.userservice.models.User;
import com.dev.userservice.repositories.SessionRepository;
import com.dev.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public ResponseEntity<UserDto> signup(UserSignupRequestDto userRequestDto) throws AlreadyExistException {
        int userCount = userRepository.getUserCountByEmail(userRequestDto.getEmail());

        if(userCount > 0){
            throw new AlreadyExistException("Please select different email id");
        }

        User user = new User();

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    public ResponseEntity<UserDto> login(UserLoginRequestDto userLoginRequestDto) throws NotFoundException {
        Optional<User> user = userRepository.findByEmail(userLoginRequestDto.getEmail());

//        if(user.isEmpty() || !user.get().getPassword().equals(userLoginRequestDto.getPassword())){
//            throw new NotFoundException("Invalid user id or password");
//        }

        if(!bCryptPasswordEncoder.matches(userLoginRequestDto.getPassword(), user.get().getPassword())){
            throw new RuntimeException("Invalid User Id or Password");
        }

        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();

//        String payload = "{ " +
//                "\"sub\":\"1234567890\"," +
//                "\"name\":\"John Doe\"," +
//                "\"iat\":1516239022}\"}";

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.get().getId());
        claims.put("name", user.get().getName());
        claims.put("email", user.get().getEmail());
        claims.put("roles", user.get().getRoles());


//        byte[] payloads = payload.getBytes(StandardCharsets.UTF_8);
        //String token = Jwts.builder().content(payloads, "text/json").signWith(key).compact();
        String token = Jwts.builder().claims(claims).signWith(key).compact();
        //String token = RandomStringUtils.randomAlphanumeric(30);


        Session session = new Session();
        session.setUser(user.get());
        session.setToken(token);
        session.setExpiryingAt(LocalDateTime.now().plusMinutes(10));
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token : " + token);
        return new ResponseEntity<>(UserDto.from(user.get()), headers, HttpStatus.OK);
    }

    public ResponseEntity<Void> logout(UserLogoutRequestDto userLogoutRequestDto) {
        Optional<Session> sessionOptional = sessionRepository.findSessionByUserIdAndToken(userLogoutRequestDto.getUserid(), userLogoutRequestDto.getToken());

        if(sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus() == SessionStatus.ENDED)
            return ResponseEntity.notFound().build();

        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<JWTResponse> validateSession(ValidateSessionTokenDto validateSessionTokenDto){
//        JwtParserBuilder jwt = Jwts.parser();
//
//        jwt.


        //first DB call to validate token
        Optional<Session> session = sessionRepository.findSessionByUserIdAndToken(validateSessionTokenDto.getUserId(), validateSessionTokenDto.getToken());

        if(session.isEmpty())
            throw new RuntimeException("Access Denied");


        if(session.isEmpty() || session.get().getSessionStatus() == SessionStatus.ENDED || LocalDateTime.now().compareTo(session.get().getExpiryingAt()) > 0)
            return null;
//            return SessionStatus.ENDED;


        JWTResponse jwtResponse = new JWTResponse();

        //second DB call to get user details
        //this could be replaced by just decoding the token as the data is present in the token
        User user = session.get().getUser();
        jwtResponse.setUserId(user.getId());
        jwtResponse.setName(user.getName());
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setRoles(user.getRoles());

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

//        return session.get().getSessionStatus();
    }
}
