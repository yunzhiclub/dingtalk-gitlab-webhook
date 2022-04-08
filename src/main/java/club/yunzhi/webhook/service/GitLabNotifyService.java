package club.yunzhi.webhook.service;

import java.io.IOException;

public interface GitLabNotifyService {
  /**
   * 处理事件
   * @param json json
   * @param eventName event
   * @throws IOException exception
   */
  void handleEventData(String json,String eventName, String secret) throws IOException;

  String getDingSecret();
}
