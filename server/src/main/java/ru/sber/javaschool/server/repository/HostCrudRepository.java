package ru.sber.javaschool.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.javaschool.server.entity.Client;


@Repository
public interface HostCrudRepository extends CrudRepository<Client, Long> {
}
