package com.generali.mailservice.mail;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MailMapper {

    MailMapper INSTANCE = Mappers.getMapper(MailMapper.class);

    Mail mapDtoToEntity(MailDto mailDto);
    MailDto mapEntityToDto(Mail mail);



}
