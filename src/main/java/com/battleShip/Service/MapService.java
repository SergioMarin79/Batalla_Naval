package com.battleShip.Service;

import com.battleShip.Repository.MapRepository;
import com.battleShip.domain.MapBattleShip;
import com.battleShip.Model.Entities.Map;
import com.battleShip.Model.Entities.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MapService {

    private MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<MapBattleShip> getAllMaps() {
        List<MapBattleShip> mapBattleShips = new ArrayList<>();
        List<Map> maps = mapRepository.getAll();
        for (Map map : maps) {
            MapBattleShip mapBattleShip = new MapBattleShip();
            mapBattleShip.setHeight(map.getHeight());
            mapBattleShip.setWidth(map.getWidth());
            mapBattleShip.setName(map.getName());
            mapBattleShips.add(mapBattleShip);
            java.util.Map<Ship, Long> result =
                    map.getShips().stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

            mapBattleShip.setShips(result);
        }



        return mapBattleShips;
    }
    public MapBattleShip getMapById (int id){
        Map map = mapRepository.getById(id);
        MapBattleShip mapBattleShip = new MapBattleShip();
        mapBattleShip.setHeight(map.getHeight());
        mapBattleShip.setWidth(map.getWidth());
        mapBattleShip.setName(map.getName());
        java.util.Map<Ship, Long> result =
                map.getShips().stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(map.getShips().size());
        mapBattleShip.setShips(result);

        return mapBattleShip;
    }

}
