package com.battleShip.Repository;

import com.battleShip.Model.Entities.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  MapRepository  extends CrudRepository<Map,Integer> {

        @Query("SELECT map FROM Map map")
        List<Map> getAll();

        @Query("SELECT map FROM Map map WHERE map.id=?1")
        Map getById(int id);


}
