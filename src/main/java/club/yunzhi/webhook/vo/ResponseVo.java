package club.yunzhi.webhook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo<T> {

  private Integer code;
  private String msg;
  private T data;

  public ResponseVo(Integer code,String msg){
    this.code = code;
    this.msg = msg;
  }
}
