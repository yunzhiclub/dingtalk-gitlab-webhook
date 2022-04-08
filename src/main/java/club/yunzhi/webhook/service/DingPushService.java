package club.yunzhi.webhook.service;

import club.yunzhi.webhook.vendor.data.MarkDownMessage;

/**
 * 钉钉推送
 */
public interface DingPushService {
  /**
   * 推送 message
   * @param markDownMessage markdown message
   */
  void pushMarkDownMessage(MarkDownMessage markDownMessage);
}
