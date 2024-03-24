package cn.hong.backend.service.music;

import cn.hong.backend.service.music.bean.Emoji;
import cn.hong.backend.service.music.bean.EmojiContainer;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.HexUtil;
import cn.hutool.json.JSONUtil;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonTest {

  private static final Random RANDOM = new Random();

  public static String getRandomEmoji(List<Emoji> emojis) {
    int randomIndex = RANDOM.nextInt(emojis.size());
    String emojiCodepoint = emojis.get(randomIndex).getCode();
    return new String(Character.toChars(HexUtil.hexToInt(emojiCodepoint)));
  }

  @Test
  public void readFile() {
    FileReader fileReader = new FileReader("json/sing-list.json");
    String singList = fileReader.readString();
    System.out.println(singList);
    List<Map> result = JSONUtil.toList(singList, Map.class);
    System.out.println(result);
    Assertions.assertNotNull(result);
  }

  @Test
  public void emoji() {
    FileReader fileReader = new FileReader("json/emoji.json");
    String emojiJson = fileReader.readString();
    System.out.println(emojiJson);
    EmojiContainer result = JSONUtil.toBean(emojiJson, EmojiContainer.class);
//        System.out.println(result);
    // List<Collection<List<Emoji>>>

    List<List<Emoji>> collect = result.getEmojis().values().stream()
        .map(Map::values).flatMap(Collection::stream).collect(Collectors.toList());// 提取内层map的值集合
// 使用Stream流拆分两层map和List
    List<Emoji> emojis = result.getEmojis().values().stream()
        .map(Map::values) // 提取内层map的值集合
        .flatMap(Collection::stream) // 扁平化流
//                .map(Map::values) // 提取List的值集合
        .flatMap(Collection::stream) // 扁平化流
        .collect(Collectors.toList());
    System.out.println(getRandomEmoji(emojis));
  }
}