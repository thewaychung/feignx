package feign.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Simple Logger implementation backed by SLF4J
 */
public class SimpleLogger extends AbstractLogger {

  private static final Logger logger = LoggerFactory.getLogger(SimpleLogger.class);

  public static Builder builder() {
    return new Builder();
  }

  /**
   * Creates a new Simple Logger.
   *
   * @param enabled if logging should be enabled.
   * @param requestEnabled if request logging should be enabled.
   * @param responseEnabled if response logging should be enabled.
   * @param headersEnabled if header logging should be enabled.
   */
  private SimpleLogger(boolean enabled, boolean requestEnabled, boolean responseEnabled,
      boolean headersEnabled) {
    super(enabled, requestEnabled, responseEnabled, headersEnabled);
  }

  /**
   * Log the Message.  Places the Method name on the MDC.
   *
   * @param methodName for context.
   * @param message to log.
   */
  @Override
  protected void log(String methodName, String message) {
    MDC.put("method", methodName);
    try {
      logger.info(message);
    } finally {
      MDC.clear();
    }
  }

  /**
   * Simple Logger Builder.
   */
  public static class Builder {

    private boolean enabled = false;
    private boolean requestEnabled = false;
    private boolean responseEnabled = false;
    private boolean headersEnabled = false;

    public Builder setEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public Builder setRequestEnabled(boolean requestEnabled) {
      this.enabled = true;
      this.requestEnabled = requestEnabled;
      return this;
    }

    public Builder setResponseEnabled(boolean responseEnabled) {
      this.enabled = true;
      this.responseEnabled = responseEnabled;
      return this;
    }

    public Builder setHeadersEnabled(boolean headersEnabled) {
      this.enabled = true;
      this.headersEnabled = headersEnabled;
      return this;
    }

    public SimpleLogger build(){
      return new SimpleLogger(this.enabled, this.requestEnabled, this.responseEnabled,
          this.headersEnabled);
    }
  }
}