package com.mertkagan.hobbyto.business.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
@Data
public class CreateUsersRequest {

    private  Long id;

    @NotNull(message = "İsim alanı boş bırakılamaz.")
    @NotEmpty(message = "İsim alanı boş bırakılamaz.")
    private String name;
    @NotNull(message = "Soyisim alanı boş bırakılamaz.")
    @NotEmpty(message = "Soyisim alanı boş bırakılamaz.")
    private String surName;
    @NotNull(message = "Kullanıcı Adı alanı boş bırakılamaz.")
    @NotEmpty(message = "İsim alanı boş bırakılamaz.")
    private String userName;
    @NotNull(message = "E-mail alanı boş bırakılamaz.")
    @NotEmpty(message = "E-mail alanı boş bırakılamaz.")
    private String email;
    @NotNull(message = "Şifre alanı boş bırakılamaz.")
    @NotEmpty(message = "Şifre  alanı boş bırakılamaz.")
    private String password;







}
