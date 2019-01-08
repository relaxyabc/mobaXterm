package com.wanna.keygen.controller;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import com.wanna.keygen.App;
import com.wanna.keygen.core.License;
import com.wanna.keygen.util.EncryptUtil;
import com.wanna.keygen.util.KeyGenerate;
import com.wanna.keygen.util.VariantBase64;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * @author wanna
 * @since 2019-01-03
 */
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private static final Pattern VERSION_MATCHER = Pattern.compile("\\d+\\.\\d+$");

    @FXML
    private TextField userName;

    @FXML
    private TextField targetVersion;

    @FXML
    private CheckBox gameBox;

    @FXML
    private CheckBox pluginBox;

    private static ExecutorService executor;

    static {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void initialize() {
        targetVersion.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                checkVersionFormat();
            }
        });
    }


    private void checkVersionFormat() {
        String version = targetVersion.getText();
        if (!VERSION_MATCHER.matcher(version).find()) {
            Alert alert = createAlert("版本号格式为 [主版本号.次版本号], 如10.9");
            alert.showAndWait();
        }
    }

    /**
     * 生成注册文件
     */
    public void register() {
        boolean openGame = gameBox.isSelected();
        boolean openPlugin = pluginBox.isSelected();
        String name = userName.getText();
        String version = targetVersion.getText();
        if (Objects.equals("", name.trim()) || Objects.equals("", version.trim())) {
            Alert alert = createAlert("请确定用户名和版本已填写");
            alert.showAndWait();
            return;
        }
        License license = new License(name, version);
        license.setOpenGames(openGame);
        license.setOpenPlugins(openPlugin);

        String licenseKey = license.getLicenseKey();
        String encryptBytes = EncryptUtil.encryptBytes(0x787, licenseKey.getBytes(StandardCharsets.UTF_8));
        String content = VariantBase64.variantBase64Encode(encryptBytes.getBytes(StandardCharsets.UTF_8));

        executor.submit(() -> {
            try {
                KeyGenerate.generate(content);
            } catch (Exception e) {
                logger.error("生成注册文件出错,{}....", e);
                Alert alert = createAlert("保存key出错,稍后请重试");
                alert.showAndWait();
            }
        });
    }

    /**
     * 创建弹窗警告
     *
     * @param content 弹窗内容
     * @return alert
     */
    private Alert createAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText(null);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(("/static/logo.png")));
        return alert;
    }

    /**
     * 打开超链接
     */
    public void openHyperlink() {
        HostServicesDelegate hostServices = HostServicesFactory.getInstance(App.getInstance());
        hostServices.showDocument("https://mobaxterm.mobatek.net/");
    }

    /**
     * 打开生成路径
     */
    public void openPath() {
        if (Desktop.isDesktopSupported()) {
            executor.submit(() -> {
                try {
                    String file = new File("").getAbsolutePath();
                    Desktop.getDesktop().browse(new File(file).toURI());
                } catch (IOException e) {
                    logger.error("打开生成文件夹出错,{}.....", e);
                }
            });
        }
    }

    /**
     * 关闭
     */
    @SuppressWarnings("All")
    public void close() {
        executor.shutdownNow();
        System.exit(-1);
    }

}
