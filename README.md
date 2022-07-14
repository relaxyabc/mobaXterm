# mobaxterm

#### 介绍

    MobaXterm-keygen 版本要求 Java 1.8
    为 Java 移植版.破解算法同原作者一样,移植成 Java 版. 
    原作者 GitHub Python 版地址为: 
    https://github.com/DoubleLabyrinth/MobaXterm-keygen.git

#### 使用说明

    1. 双击 mobaxterm.jar 
    2. 输入用户名、版本号
    3. 点击注册,点击打开,找到Custom.mxtpro,到 mobaxterm 安装目录即可.
       如 C:/Program Files (x86)/Mobatek/MobaXterm
    4:官方下载网址:https://mobaxterm.mobatek.net/

#### 注意

    由于 Java 原生的 ZipOutputStream 生成的Custom.mxtpro不具备破解效果,
    故采用 先生成 Pro.key 文件,然后调用命令利用 7z 生成 zip包,最后重命名为 Custom.mxtpro.
    
    如果程序生成的 Custom.mxtpro 文件不具备破解效果,请解压文件,手动创建 zip压缩文件,把Pro.key
    放入 zip 压缩文件中,最后重命名为 Custom.mxtpro

### 更新

    现已提供 基于 Java 原生的 ZipOutputStream 生产的 Custom.mxtpro.
    详见: com.wanna.keygen.util.impl.JavaGenerator#generate

### 
    本仓库 Java 版本要求 Java 1.8 ,基于 JavaFX 开发 GUI;自从 Java 9 开始, 
    JavaFX 从 JDK 中剔除,需要单独安装,为避免版本升级导致JDK(JRE)没有 JavaFX API 的问题,该工具用 Golang 重写.
    
    Golang 版本 见仓库 https://github.com/luckylocode/Moba-Xterm
    
    Golang 没有官方的 UI 库, 本仓库 选用 fyne 这个库开发, 使用 fyne-cross 交叉编译.
    
    原作者 GitHub Python 版地址为:
    https://github.com/DoubleLabyrinth/MobaXterm-keygen.git

#### Jar执行文件下载

[下载](https://gitee.com/luckylo/mobaxterm/tags)
