package club.yunzhi.webhook.Exception;

public class FailedHandleHttpResponseException extends RuntimeException{
  public FailedHandleHttpResponseException(String message) {
    super(message);
  }

  public FailedHandleHttpResponseException(String msg, Exception e) {
    super(msg, e);
  }
}
