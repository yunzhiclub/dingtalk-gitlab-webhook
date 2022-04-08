package club.yunzhi.webhook.Exception;

public class FailedHttpCallingException extends RuntimeException{
  private final int code;
  private final String msg;
  public FailedHttpCallingException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }
}
