package com.yasu.Foody.account.service.impl;

import com.yasu.Foody.account.dto.LoginDto;
import com.yasu.Foody.account.entity.AddressEntity;
import com.yasu.Foody.account.entity.SellerUserEntity;
import com.yasu.Foody.account.entity.UserEntity;
import com.yasu.Foody.account.entity.model.SellerUserSaveReq;
import com.yasu.Foody.account.entity.model.UserSaveReq;
import com.yasu.Foody.account.entity.roles.ERole;

import com.yasu.Foody.account.repository.AddressRepository;
import com.yasu.Foody.account.repository.RoleRepository;
import com.yasu.Foody.account.repository.SellerUserRepository;
import com.yasu.Foody.account.repository.UserRepository;

import com.yasu.Foody.account.response.LoginMesage;


import com.yasu.Foody.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.rmi.AlreadyBoundException;
import java.util.*;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final SellerUserRepository sellerUserRepository;


    private final RoleRepository roleRepository;

    @Override
    public Mono<AddressEntity> createUser(UserSaveReq userSaveReq){

        if (userSaveReq.getUserName().isEmpty() || userSaveReq.getEmail().isEmpty()) {
          return   Mono.error(new AlreadyBoundException("Boş değer "));
        }
        else {
            String c = this.passwordEncoder.encode(userSaveReq.getPassword());
            AddressEntity address=AddressEntity.builder()
                    .AddressName(userSaveReq.getAddressName())
                    .UserId(userSaveReq.getId())
                    .Address(userSaveReq.getAddress())
                    .build();

            UserEntity userEntity = UserEntity.builder().id(userSaveReq.getId())
                    .userName(userSaveReq.getUserName())
                    .email(userSaveReq.getEmail())
                    .password(c)
                    .cellphone(userSaveReq.getCellphone())
                    .userType(userSaveReq.getUserType())
                    .enabled(true)
                    .roles(List.of(ERole.ROLE_USER))
                    .build();
            SellerUserEntity sellerUserEntity;
            if(userSaveReq.getUserType().equals("Seller")) {
                     sellerUserEntity = SellerUserEntity
                        .builder()
                        .userId(userSaveReq.getId())
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
                                        sellerUserEntity.setUserId(savedUser.getId()); // SellerUserEntity'ye de UserID ekleyin
                                        return sellerUserRepository.save(sellerUserEntity)
                                                .then(addressEntityMono); // AddressEntity'nin tamamlanmasını bekleyin
                                    });
                        }))
                        .cast(AddressEntity.class);
            }
            return userRepository.findUserByEmail(userSaveReq.getEmail())
                    .flatMap(__ -> Mono.error(new AlreadyBoundException("Dayı sen burdasın ")))
                    .switchIfEmpty(Mono.defer(() -> {
                        // Adresi kaydetmek için Mono<AddressEntity> oluştur
                        Mono<UserEntity> userSaveMono =userRepository.save(userEntity);
                                // Kullanıcıyı kaydetmek için Mono<UserEntity> oluştur
                        Mono<AddressEntity> addressEntityMono = userSaveMono.flatMap(savedAddress -> {
                            // AddressEntity'nin id'sini UserEntity'de ayarla
                            address.setUserId(savedAddress.getId());
                            return addressRepository.save(address);
                        });
                        // Kullanıcı kaydetme işlemi tamamlandığında userSaveMono'yu dön
                        return addressEntityMono;
                    }))
                    .cast(AddressEntity.class);



        }//    return Mono.error(new AlreadyBoundException("Dayı sen burdasın aq"));
    }
    @Override
    public Mono<UserEntity> findUserByEmail(String username) {

        return userRepository.findUserByEmail(username);
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



