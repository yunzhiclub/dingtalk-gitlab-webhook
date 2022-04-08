package club.yunzhi.webhook.request;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * GitLab请求
 */
@Data
public abstract class ParentRequest {
  private String objectKind;
  private String projectId;
  private Project project;
  private Repository repository;

  @Data
  public static class Project{
    private Long id;
    private String name;
    private String description;
    private String webUrl;
    private String avatarUrl;
    private String gitSshUrl;
    private String gitHttpUrl;
    private String namespace;
    private String visibilityLevel;
    private String pathWithNamespace;
    private String defaultBranch;

  }

  @Data
  public static class Repository{
    private String name;
    private String url;
    private String description;
    private String homePage;
  }

  @Data
  public static class Commit{
    private String id;
    private String message;
    private ZonedDateTime zonedDateTime;
    private Author author;
    private String url;

    @Data
    public static class Author{
      private String name;
      private String email;
    }
  }
}
