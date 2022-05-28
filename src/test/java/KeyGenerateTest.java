import com.wanna.keygen.core.License;
import com.wanna.keygen.util.EncryptUtil;
import com.wanna.keygen.util.VariantBase64;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class KeyGenerateTest {


    @Test
    public void test3() throws Exception {
        License license = new License("wanna", "11.0");
        String string = license.getLicenseKey();
        String content = VariantBase64.variantBase64Encode(
                EncryptUtil.encryptBytes(0x787, string.getBytes(StandardCharsets.UTF_8))
                        .getBytes(StandardCharsets.UTF_8)
        );
        System.out.println(content);

        File zipFile = new File("Custom.mxtpro");
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = new ZipEntry("Pro.key");
        CRC32 crc32 = new CRC32();
        crc32.update(content.getBytes());
        entry.setCrc(crc32.getValue());
        entry.setSize(content.length());
        entry.setMethod(ZipEntry.STORED);
        zipOut.putNextEntry(entry);
        zipOut.write(content.getBytes());
        zipOut.closeEntry();
        zipOut.close();
        System.out.println(11);
    }
}
