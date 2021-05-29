package utils;

public class CloseHelper {

    public static void close(AutoCloseable closeable) {
        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
