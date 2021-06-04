package com.battleShip.Repository;

import com.battleShip.Model.Entities.Shoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ShootRepository  extends CrudRepository<Shoot,Integer> {


}
