package club.yunzhi.webhook;

import java.util.HashMap;
import java.util.Map;

/**
 * 建立哈希映射，用于根据X-Gitlab-Event获取BeanName
 */
public class EventMapper {
  public static Map<String, String> EVENT_HANDLE_MAP = new HashMap();

  static {
    EVENT_HANDLE_MAP.put("Push Hook", "pushEventService");
  }

  public static String getHandleBeanName(String eventName) {
    return EVENT_HANDLE_MAP.get(eventName);
  }

}
