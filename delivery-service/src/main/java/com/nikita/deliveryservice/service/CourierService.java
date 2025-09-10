package com.nikita.deliveryservice.service;

import com.nikita.deliveryservice.mapper.CourierMapper;
import com.nikita.deliveryservice.model.dto.courier.CourierDto;
import com.nikita.deliveryservice.model.dto.courier.CourierRequestDto;
import com.nikita.deliveryservice.model.entity.courier.CourierEntity;
import com.nikita.deliveryservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    @Transactional
    public ResponseEntity<CourierDto> createCourier(CourierRequestDto request) {
        CourierEntity entity = CourierMapper.toEntity(request); //TODO зачем я это делаю тут просто сохраняй request? проверь потом если хватит времени
        CourierEntity courierEntity = courierRepository.save(entity);
        CourierDto dto = CourierMapper.toDto(courierEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @Transactional(readOnly = true)
    public ResponseEntity<CourierDto> findById(UUID courierId) {
        CourierEntity entity = courierRepository.findById(courierId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Курье не найден"));
        CourierDto dto = CourierMapper.toDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> deleteCourier(UUID courierId) {
        CourierEntity entity = courierRepository.findById(courierId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Курьер не найден"));
        courierRepository.delete(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Transactional
    public ResponseEntity<CourierDto> updateCourier(UUID courierId, CourierDto request) {
        CourierEntity entity = courierRepository.findById(courierId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Курьер не найден"));
        if (entity.getName() != null)
            entity.setName(request.getName());
        //TODO тут вообще надо сделать валидацию на случай если у нас уже есть такой email и phone в бд тогда нельзя
        if (entity.getEmail() != null)
            entity.setEmail(request.getEmail());
        if (entity.getPhone() != null)
            entity.setPhone(request.getPhone());
        CourierEntity courierEntity = courierRepository.save(entity);
        CourierDto dto = CourierMapper.toDto(courierEntity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
