package com.yasu.Foody.account.service.impl;

import com.yasu.Foody.Mail.MailService;
import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.dto.UserDto;
import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.EsVerificationCode;
import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.AssignRoleReq;
import com.yasu.Foody.account.entity.model.UserActivateReq;
import com.yasu.Foody.account.entity.model.UserDeleteReq;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.account.entity.roles.ERole;

import com.yasu.Foody.account.entity.roles.Role;
import com.yasu.Foody.account.repository.*;

import com.yasu.Foody.account.service.VerificationCodeService;
import com.yasu.Foody.security.dto.Messag;
import com.yasu.Foody.security.dto.Message;
import com.yasu.Foody.security.response.LoginMesage;


import com.yasu.Foody.account.service.UserService;

import lombok.RequiredArgsConstructor;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.AlreadyBoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final SellerUserRepository sellerUserRepository;

    private final MailService mailService;
    private final VerificationCodeService verificationCodeService;
    private final EsVerificationCodeRepo esVerificationCodeRepo;

    private final RoleRepository roleRepository;

    private final AtomicLong userIdCounter = new AtomicLong(0);
    private final AtomicLong addressIdCounter = new AtomicLong(0);
    private final AtomicLong sUserIdCounter = new AtomicLong(0);

    private Long userID(){
         return userIdCounter.incrementAndGet();
    }
    private Long addressID(){
        return addressIdCounter.incrementAndGet();
    }
    private Long sUserID(){

        return sUserIdCounter.incrementAndGet();
    }
    @Override
    public Mono<AddressEntity> createUser(UserSaveReq userSaveReq) {
        if (userSaveReq.getUserName().isEmpty() || userSaveReq.getEmail().isEmpty()) {
            return Mono.error(new AlreadyBoundException("Boş değer giremezsiniz "));
        } else {
            String c = this.passwordEncoder.encode(userSaveReq.getPassword());

            return sellerNameChecker(userSaveReq.getSellerName())
                    .flatMap(message -> {
                        if ("VAR".equals(message.getContent())) {
                            return Mono.error(new AlreadyBoundException("Bu satıcı ismi zaten var!"));
                        } else {
                            // Satıcı adı kontrolü başarılı olduğunda createUser işlemini gerçekleştirin.
                            return createUserInternal(userSaveReq, c);
                        }
                    })
                    .switchIfEmpty(Mono.error(new AlreadyBoundException("Satıcı adı kontrolü başarısız oldu!")));
        }
    }

    private Mono<AddressEntity> createUserInternal(UserSaveReq userSaveReq, String encryptedPassword) {
        AddressEntity address = AddressEntity.builder()
                .AddressName(userSaveReq.getAddressName())
                .UserId(userID())
                .Address(userSaveReq.getAddress())
                .id(addressID())
                .build();

        UserEntity userEntity = UserEntity.builder().id(userID())
                .userName(userSaveReq.getUserName())
                .email(userSaveReq.getEmail())
                .password(encryptedPassword)
                .cellphone(userSaveReq.getCellphone())
                .userType(userSaveReq.getUserType())
                .enabled(true)
                .roles(List.of(ERole.ROLE_USER))
                .build();

        if (userSaveReq.getUserType().equals("Seller")) {
            SellerUserEntity sellerUserEntity = SellerUserEntity.builder().id(sUserID())
                    .userId(userEntity.getId())
                    .vergiNo(userSaveReq.getVergiNo())
                    .sellerName(userSaveReq.getSellerName())
                    .sellerAddress(userSaveReq.getSellerAddress())
                    .build();

            return userRepository.findUserByEmail(userSaveReq.getEmail())
                    .flatMap(__ -> Mono.error(new AlreadyBoundException("Dayı sen burdasın ")))
                    .switchIfEmpty(Mono.defer(() -> {
                        return userRepository.save(userEntity)
                                .flatMap(savedUser -> {
                                    address.setUserId(savedUser.getId());
                                    Mono<AddressEntity> addressEntityMono = addressRepository.save(address);
                                    sellerUserEntity.setUserId(savedUser.getId());
                                    return sellerUserRepository.save(sellerUserEntity)
                                            .then(addressEntityMono);
                                });
                    }))
                    .cast(AddressEntity.class);
        } else {
            return userRepository.findUserByEmail(userSaveReq.getEmail())
                    .flatMap(__ -> Mono.error(new AlreadyBoundException("Dayı sen burdasın ")))
                    .switchIfEmpty(Mono.defer(() -> {
                        Mono<UserEntity> userSaveMono = userRepository.save(userEntity);
                        Mono<AddressEntity> addressEntityMono = userSaveMono.flatMap(savedAddress -> {
                            address.setUserId(savedAddress.getId());
                            return addressRepository.save(address);
                        });
                        return addressEntityMono;
                    }))
                    .cast(AddressEntity.class);
        }
    }



    @Override
    public Mono<Role> roleAdd( Role eRole){
       return roleRepository.save(eRole);
    }

    @Override
    public Mono<UserDto> assignRole(AssignRoleReq req) {
        return userRepository.findById(req.getId())
                .switchIfEmpty(Mono.error(new AlreadyBoundException("Kullanıcı bulunamadı.")))
                .flatMap(userEntity -> {
                    userEntity.setRoles(req.getRoles());
                    // Entity'yi güncelle
                  return this.convertToDTO(userRepository.save(userEntity)) ; // Entity'yi DTO'ya dönüştür

                });
    }
    private Mono<UserDto> convertToDTO(Mono<UserEntity> userEntity) {
        if(userEntity==null) {
            return null;
        }
     return    userEntity.map(userEntity1 -> UserDto.builder()
                .userName(userEntity1.getUsername())
                .cellphone(userEntity1.getCellphone())
                .enabled(userEntity1.getEnabled())
                .email(userEntity1.getEmail())
                .build());
    }

    @Override
    public Mono<Message> userDelete(UserDeleteReq req) {
        return userRepository.findById(req.getId())
                .flatMap(user -> {
                    if (!user.getEnabled()) {
                        return Mono.error(new Message("Kullanıcı devre dışı zaten"));
                    }

                    user.setEnabled(false);

                    return userRepository.save(user)
                            .thenReturn(new Message("Kullanıcı hesabı başarıyla devre dışı bırakıldı"));
                })
                .switchIfEmpty(Mono.error(new Message("Kullanıcı bulunamadı")));
    }


    @Override
     public   Mono<Message> activationCode(UserActivateReq userActivateReq){
        return userRepository.findUserByEmail(userActivateReq.getEmail())
                .map(user->
                {
                  //  user.setPassword(passwordEncoder.encode(userActivateReq.getPassword()));
                        String code=  verificationCodeService.randomCode(user.getId(),user.getEmail());
                        mailService.sendMail("yusuf-oflu61@hotmail.com",code);
                 return new Message("Kullanıcı Onay Kodu Gönderildi.");


                })
                .switchIfEmpty(Mono.error(new Message("Kullanıcı bulunamadı")));
    }
    @Override
    public Mono<UserEntity> verificationCode(EsVerificationCode esVerificationCode) {
        return esVerificationCodeRepo.findByUserId(esVerificationCode.getUserId()).flatMap(userVcode -> {
            if (esVerificationCode.getCode().equals(userVcode.getCode())) {
                return userRepository.findById(userVcode.getUserId()).flatMap(user -> {
                    user.setEnabled(true);
                    return userRepository.save(user);
                });
            }
            return Mono.error(new Message("Kullanıcı Onay Kodu Doğru Girilmedi."));
        });
    }



    private Mono<Message> updatePassword(){
        return null;
    }



    private Mono<Message> sellerNameChecker(String sellername) {
        return sellerUserRepository.findBysellerName(sellername)
                .flatMap(sellerUser -> Mono.just(new Message("VAR"))) // Satıcı adı bulunduğunda
                .switchIfEmpty(Mono.just(new Message("YOK"))); // Satıcı adı bulunamadığında
    }
    @Override
    public Mono<UserEntity> findUserByEmail(String username) {

        return userRepository.findUserByEmail(username);
    }

    @Override
    public Mono<SellerUserEntity> findUserById(Long id) {
        return sellerUserRepository.findById(id);
    }


    @Override
    public UserEntity findUserBy(String username) {
        return null;
    }

    @Override
    public LoginMesage login(LoginDto loginDto) {
      /*
        String msg="";
       UserEntity dt=(userRepository.findByEmail(loginDto.getEmail()).block());
        if (dt!=null){
            String pass=loginDto.getPassword();
            String encodedPassword=dt.getPassword();
            Boolean isPsdRight=passwordEncoder.matches(pass,encodedPassword);
            if(isPsdRight){
                Optional<UserEntity> usr=userRepository.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
                if(usr.isPresent())
                    return new LoginMesage("Login Success",true);
                else
                    return new LoginMesage("Login Failed",false);
            }
            else
                return new LoginMesage("Password Not Match!",false);

        }
        return new LoginMesage("Email not exist",false);
*/
        return null;

    }



    @Override
    public Flux<UserEntity> findAll() {

        return userRepository.findAll();
    }



    }



