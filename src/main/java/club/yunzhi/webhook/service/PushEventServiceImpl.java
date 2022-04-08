package club.yunzhi.webhook.service;

import club.yunzhi.webhook.constant.MessageTypeConstant;
import club.yunzhi.webhook.request.GitLabPushRequest;
import club.yunzhi.webhook.util.JsonUtil;
import club.yunzhi.webhook.vendor.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("pushEventService")
@Slf4j
public class PushEventServiceImpl implements EventService {
  @Autowired
  private DingPushService dingPushService;

  @Override
  public void handleEvent(String json) throws IOException {
    GitLabPushRequest gitLabPushRequest = covertJson(json);
    String projectLink = "[" + gitLabPushRequest.getProject().getName() +
            "]" + "(" + gitLabPushRequest.getProject().getWebUrl() + ")";
    String latestCommitLink = "最新一次commit:" + "[" + gitLabPushRequest.getCommits().get(0).getMessage() +
            "]" + "(" + gitLabPushRequest.getCommits().get(0).getUrl() + ")";
    String text = "## " + gitLabPushRequest.getEventName() + "  \n" +
            projectLink + "  \n" +
            gitLabPushRequest.getUserUsername() + "提出了push请求" + "  \n" +
            latestCommitLink;
    dingPushService.pushMarkDownMessage(new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown(gitLabPushRequest.getObjectKind(), text)));
  }

  /**
   * 反序列化——把字节恢复为Java对象
   *
   * @param json json
   * @return Java对象
   */
  private GitLabPushRequest covertJson(String json) throws IOException {
    return JsonUtil.deserializeFromJson(json, GitLabPushRequest.class);
  }
}
