package cn.hong.backend.service.music.bean;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 *
 */
@Data
public class EmojiContainer {

  private Map<String, Map<String, List<Emoji>>> emojis;
}
