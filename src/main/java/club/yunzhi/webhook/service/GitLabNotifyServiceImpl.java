package club.yunzhi.webhook.service;

import club.yunzhi.webhook.EventMapper;
import club.yunzhi.webhook.configuration.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GitLabNotifyServiceImpl implements GitLabNotifyService {
  /**
   * 用于缓存X-Gitlab-Token
   */
  String secret;

  /**
   * 用于根据name获取bean
   */
  @Autowired
  private ApplicationContextProvider applicationContextProvider;

  @Override
  public void handleEventData(String json, String eventName, String secret) throws IOException {
    //缓存secret
    this.secret = secret;
    String handleBeanName = EventMapper.getHandleBeanName(eventName);
    EventService eventService = (EventService) applicationContextProvider.getBean(handleBeanName);
    eventService.handleEvent(json);
  }

  @Override
  public String getDingSecret() {
    return secret;
  }
}
