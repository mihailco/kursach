package harakiri.security.filter;

public class UserContextHolder {
    private static final ThreadLocal<Long> threadLocalId = new ThreadLocal<>();

    public static void setId(Long value) {
        threadLocalId.set(value);
    }

    public static Long getId() {
        return threadLocalId.get();
    }

    public static void clearId() {
        threadLocalId.remove();
    }
}
