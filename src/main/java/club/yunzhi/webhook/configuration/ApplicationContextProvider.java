package club.yunzhi.webhook.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 通过name获取Bean
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

  /**
   * 上下文对象实例
   */
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  /**
   * 获取applicationContext
   */
  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 通过name获取 Bean.
   */
  public Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }
}
