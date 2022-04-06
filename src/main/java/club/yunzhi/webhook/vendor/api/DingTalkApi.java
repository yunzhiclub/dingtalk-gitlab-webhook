package club.yunzhi.webhook.vendor.api;

import club.yunzhi.webhook.vendor.DingResponse;
import club.yunzhi.webhook.vendor.data.MarkDownMessage;

/**
 * 钉钉接口
 */
public interface DingTalkApi {

  /**
   * 向钉钉接口发送 markDownMessage
   * @param markDownMessage markDownMessage
   * @return
   */
  DingResponse<Void> pushMarkDownMessage(MarkDownMessage markDownMessage);


  /**
   * 用于对接钉钉加签加密
   * @return
   * @throws Exception
   */
  String encode(String secret) throws Exception;
}
