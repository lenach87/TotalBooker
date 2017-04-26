package com.lenach.totalbooker.data.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * Created by o.chubukina on 24/04/2017.
 */

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

        @Override
        public Long convertToDatabaseColumn(Duration localDuration) {
            return (localDuration == null ? null : localDuration.toMinutes());
        }

        @Override
        public Duration convertToEntityAttribute(Long sqlDuration) {
            return (sqlDuration == null ? null : Duration.ofMinutes(sqlDuration));
        }

}

