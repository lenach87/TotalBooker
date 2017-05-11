package com.lenach.totalbooker.controllers.mappers;

import com.lenach.totalbooker.controllers.viewmodel.BookingViewModel;
import com.lenach.totalbooker.data.Booking;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by o.chubukina on 10/05/2017.
 */

@Mapper (componentModel = "spring")
public interface BookingMapper {

    Booking bookingFromBookingModel(BookingViewModel bookingVM);

    @InheritInverseConfiguration
    BookingViewModel bookingToBookingModel(Booking booking);
}

