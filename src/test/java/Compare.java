import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wanna
 * @since 2019-01-04
 */
public class Compare {

    public static void main(String[] args) throws IOException {
        byte[] copy = getByteArray("copy.mxtpro");
        byte[] java = getByteArray("java.mxtpro");
        byte[] py = getByteArray("py.mxtpro");
        return;

    }

    private static byte[] getByteArray(String name) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(name);
        byte[] bytes = new byte[1024 * 1024 * 10];
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }
}
