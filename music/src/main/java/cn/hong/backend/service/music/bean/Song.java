package cn.hong.backend.service.music.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("song")
public class Song {

  @Id
  private int id;

  private String name;


}
