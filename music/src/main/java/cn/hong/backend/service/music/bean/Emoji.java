package cn.hong.backend.service.music.bean;

import java.util.List;
import lombok.Data;

/**
 *
 */
@Data
public class Emoji {

  private String id;
  private String code;
  private String address;
  private String image;
  private String description;
  private List<String> tag;

}
