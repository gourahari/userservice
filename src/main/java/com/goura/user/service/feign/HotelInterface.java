package com.goura.user.service.feign;

import com.goura.user.service.entitiy.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("HOTELSERVICE")
public interface HotelInterface {
    @PostMapping("hotels")
    ResponseEntity<String> create(@RequestBody Hotel hotel);

    @GetMapping("hotels")
    ResponseEntity<List<Hotel>> getAll();

    @GetMapping("/hotels/{id}")
    ResponseEntity<Hotel> getById(@PathVariable String id);

}
