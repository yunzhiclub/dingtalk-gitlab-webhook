package club.yunzhi.webhook.Exception;

public class UnknownException extends RuntimeException{
  public UnknownException() {
  }

  public UnknownException(String message) {
    super(message);
  }

}
