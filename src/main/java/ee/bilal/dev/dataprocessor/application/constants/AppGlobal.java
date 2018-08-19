package ee.bilal.dev.dataprocessor.application.constants;

/**
 * Created by bilal90 on 8/19/2018.
 */
public final class AppGlobal {
  private AppGlobal()
  {
    throw new AssertionError();
  }

  public static final String AUTHORIZATION = "Authorization";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
  public static final String UTF8 = "UTF-8";
  public static final String TIMESTAMP = "timestamp";
  public static final String STATUS = "status";
  public static final String ERROR = "error";
  public static final String EXCEPTION = "exception";
  public static final String MESSAGE = "message";
}
