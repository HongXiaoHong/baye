package cn.hong.backend.service.music;

import cn.hong.backend.service.music.bean.Song;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@SpringBootTest
class MusicApplicationTests {

  @Autowired
  MongoTemplate mongoTemplate;

  @Test
  void contextLoads() {
    Song song = Song.builder().id(1).name("往事随风").build();
    mongoTemplate.remove(song);
    mongoTemplate.insert(song);
    Song result = mongoTemplate.findById(1, Song.class);
    log.info("result: {}", result);
  }

}
