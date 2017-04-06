package com.lenach.totalbooker.service;

/**
 * Created by o.chubukina on 06/04/2017.
 */
@
public interface BookingService {
    Device save (Device device);

    Page<Device> findAll(Pageable pageable);

    Device findOne(Long id);

    Page<Device> findByPattern(String pattern, Pageable pageable);

    void delete(Long id);
}
