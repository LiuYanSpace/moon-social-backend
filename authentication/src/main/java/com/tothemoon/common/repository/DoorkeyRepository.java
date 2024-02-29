package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DoorKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoorkeyRepository extends JpaRepository<DoorKey, Long> {

    Optional<DoorKey> findByKey(String key);
}
