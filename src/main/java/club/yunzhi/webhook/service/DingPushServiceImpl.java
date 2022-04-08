package club.yunzhi.webhook.service;

import club.yunzhi.webhook.Exception.UnknownException;
import club.yunzhi.webhook.vendor.DingResponse;
import club.yunzhi.webhook.vendor.api.DingTalkApi;
import club.yunzhi.webhook.vendor.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DingPushServiceImpl implements DingPushService {
  private static final String SUCCESSS_CODE = "0";

  @Autowired
  private DingTalkApi dingTalkApi;

  @Override
  public void pushMarkDownMessage(MarkDownMessage markDownMessage) {
    DingResponse<Void> response = dingTalkApi.pushMarkDownMessage(markDownMessage);
    if (!SUCCESSS_CODE.equals(response.getErrcode())) {
      throw new UnknownException("error");
    }
  }
}
