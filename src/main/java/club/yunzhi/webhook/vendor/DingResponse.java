package club.yunzhi.webhook.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 钉钉响应实体
 */
@Data
public class DingResponse<T> {
  @JsonProperty("errmsg")
  private String errMsg;
  @JsonProperty("errcode")
  private String errcode;
  private T data;
}
