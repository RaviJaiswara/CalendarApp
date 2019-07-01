package cerebrik.com.calendarexample.alarm;

public abstract class BaseController<V> {
    protected final V view;

    protected BaseController(V view) {
        this.view = view;
    }
}
