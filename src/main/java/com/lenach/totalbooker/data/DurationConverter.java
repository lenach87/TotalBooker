package com.lenach.totalbooker.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by o.chubukina on 24/04/2017.
 */

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

        @Override
        public Long convertToDatabaseColumn(Duration localDuration) {
            return (localDuration == null ? null : localDuration.toMillis());
        }

        @Override
        public Duration convertToEntityAttribute(Long sqlDuration) {
            return (sqlDuration == null ? null : Duration.ofMillis(sqlDuration));
        }

}

