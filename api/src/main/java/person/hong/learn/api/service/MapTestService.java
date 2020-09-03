package person.hong.learn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import person.hong.learn.api.entity.MapTest;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/3 23:31
 */

@Service
public class MapTestService {
    @Autowired
    MapTest mapTest;
    public void sout() {
        System.out.println("mapTest : " + mapTest.getMaps());
    }

/*    public MapTest getMapTest() {
        return mapTest;
    }

    public void setMapTest(MapTest mapTest) {
        this.mapTest = mapTest;
    }*/
}
