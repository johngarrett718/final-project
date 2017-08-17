package org.launchcode.finalproject.models.data;


import org.launchcode.finalproject.models.SalesRepModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface SalesRepDao extends CrudRepository<SalesRepModel, Integer> {
}
