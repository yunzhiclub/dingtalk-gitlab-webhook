package club.yunzhi.webhook.vendor.api;

import club.yunzhi.webhook.util.HttpClientResponse;
import club.yunzhi.webhook.vendor.DingResponse;
import club.yunzhi.webhook.vendor.data.MarkDownMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Collections;

public class DingTalkApiImpl implements DingTalkApi {

  /**
   * 从application.properties获取设置好的dingTalkUrl
   */
  @Value("${vendor.ding.dingTalkUrl}")
  private String dingTalkUrl;

  /**
   * todo
   * 构造POST请求，向钉钉接口发出markDownMessage
   * @param markDownMessage markDownMessage
   * @return
   */
  @Override
  public DingResponse<Void> pushMarkDownMessage(MarkDownMessage markDownMessage) {
    HttpClientResponse httpClientResponse;
    return null;
  }

  /**
   * 与钉钉机器人加签操作对接
   * @param secret X-Gitlab-Token
   */
  @Override
  public String encode(String secret) throws Exception {
    //获取时间戳
    Long timestamp = System.currentTimeMillis();
    //把时间戳和密钥拼接成字符串，中间加入一个换行符
    String stringToSign = timestamp + "\n" + secret;
    //声明一个Mac对象，用来操作字符串
    Mac mac = Mac.getInstance("HmacSHA256");
    //初始化，设置Mac对象操作的字符串是UTF-8类型，加密方式是SHA256
    mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
    //把字符串转化成字节形式
    byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
    //新建一个Base64编码对象
    Base64.Encoder encoder = Base64.getEncoder();
    //把上面的字符串进行Base64加密后再进行URL编码
    String sign = URLEncoder.encode(new String(encoder.encodeToString(signData)),"UTF-8");
    String result = "&timestamp=" + timestamp + "&sign=" + sign;
    return result;
  }
}
