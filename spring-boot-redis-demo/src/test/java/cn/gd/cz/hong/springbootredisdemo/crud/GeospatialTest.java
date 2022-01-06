package cn.gd.cz.hong.springbootredisdemo.crud;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;

import javax.annotation.PostConstruct;

/**
 * Geospatial 地理位置
 */
@Slf4j
@SpringBootTest
public class GeospatialTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private GeoOperations<String, Object> geoOpes;

    @PostConstruct
    public void init() {
        geoOpes = redisTemplate.opsForGeo();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        ImmutableMap<Object, Point> cites = ImmutableMap.<Object, Point>builder()
                .put("beijing", new Point(116.405285, 39.904989))
                .put("shanghai", new Point(121.472644, 31.231706))
                .put("wuhan", new Point(114.298572, 30.584355))
                .put("changsha", new Point(112.982279, 28.19409))
                .put("guangzhou", new Point(113.280637, 23.125178))
                .put("chaozhou", new Point(116.632301, 23.661701))
                .build();
        // geoadd
        // # 规则：两级无法直接添加，我们一般会下载城市数据，直接通过java程序一次性导入！
        // # 有效的经度从-180度到180度。
        // # 有效的纬度从-85.05112878度到85.05112878度
        geoOpes.add("cites", cites);
        // GEOPOS 获取指定的城市的经度和纬度！
        log.error("GEOPOS - cites - guangzhou: {}", geoOpes.position("cites", "guangzhou"));
        // GEODIST 两人之间的距离！
        log.error("geodist cites - guangzhou to chaozhou distance: {}", geoOpes.distance("cites", "guangzhou", "chaozhou", Metrics.KILOMETERS));
        // georadius 以给定的经纬度为中心， 找出某一半径内的元素
        log.error("georadius cites - radius: {}", geoOpes.radius("cites", "guangzhou", new Distance(500, Metrics.KILOMETERS)));
        // GEO 底层的实现原理其实就是 Zset！我们可以使用Zset命令来操作geo
    }
    /*
     * GEOPOS - cites - guangzhou: [Point [x=113.280638, y=23.125177]]
     * geodist cites - guangzhou to chaozhou distance: 347.305 KILOMETERS
     * georadius cites - radius: GeoResults: [averageDistance: 0.0, results: GeoResult [content: RedisGeoCommands.GeoLocation(name=guangzhou, point=null), distance: 0.0, ],GeoResult [content: RedisGeoCommands.GeoLocation(name=chaozhou, point=null), distance: 0.0, ]]
     */
}
