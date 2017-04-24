package com.lenach.totalbooker.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by o.chubukina on 24/04/2017.
 */

@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Long> {

        @Override
        public Long convertToDatabaseColumn(LocalDateTime localDate) {
            return (localDate == null ? null : localDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }

        @Override
        public LocalDateTime convertToEntityAttribute(Long sqlDate) {
            return (sqlDate == null ? null :  Instant.ofEpochMilli(sqlDate).atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

    }

