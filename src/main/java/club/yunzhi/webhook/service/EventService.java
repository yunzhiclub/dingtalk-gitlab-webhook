package club.yunzhi.webhook.service;

import java.io.IOException;

/**
 * 事件接口
 */
public interface EventService {

  /**
   * 处理事件
   * @param json data
   * @throws IOException exception
   */
  void handleEvent(String json) throws IOException;
}
