package club.yunzhi.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitLabPushRequest extends ParentRequest {
  private String before;
  private String after;
  private String ref;
  private String checkoutSha;
  private String userId;
  private String userName;
  private String userUsername;
  private String userAvatar;
  private List<Commit> commits;
  private String eventName;

  private int totalCommitsCount;
}
