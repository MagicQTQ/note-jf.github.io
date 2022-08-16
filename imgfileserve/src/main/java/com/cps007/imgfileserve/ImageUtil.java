package com.cps007.imgfileserve;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * png、jpg图片大小压缩，svg不用压缩。
 * <p>
 * 格式：type-iamge-xxx.[png|jpg]。（type：true已经压缩，false还没有压缩）
 * <p>
 * 1、把所有还没有处理的图片文件重命名为 false- 开头。
 * <p>
 * 2、处理false开头的图片<p>
 * 2.1、压缩<p>
 * 2.2、文件重命名为true-开头<p>
 * 2.3、更新md文件<p>
 *
 * @author kong19
 * @version 1.0
 * @date 2022/4/26 16:35
 */
@Slf4j
public class ImageUtil {
    /**
     * 获取当前项目处在的目录
     * <p>
     * 亦可自定义
     */
    private final static String PROJECT_PATH = System.getProperty("user.dir");
//    private final static String PROJECT_PATH = "E:\\code\\idea\\imgfileserve";
    /**
     * 需要自动批量处理的md文件目录，与 PROJECT_PATH 配合
     */
    private final static String MD_PATH = "\\docs";
    /**
     * 本项目名称，用于测试时使用
     */
    private final static String PROJECT_NAME = "\\imgfileserve";
    /**
     * 测试使用，md文件处于本项目内：md2 为原始数据源，需要复制到 md，
     * 有便于多次测试
     */
    private final static String TEST_MD_PATH = "\\md";
    private final static String SPLICING = "\\";
    /**
     * 开头
     */
    private final static String PREFIX = ".";
    private final static String PREFIX_FALSE = "false-";
    private final static String PREFIX_TRUE = "true-";
    /**
     * 结尾
     */
    private final static String SUFFIX_ASSETS = "assets";
    private final static String SUFFIX_MD = "md";
    private final static String SUFFIX_PNG = "png";
    private final static String SUFFIX_JPG = "jpg";
    private final static String SUFFIX_JPEG = "jpeg";
    private static File OLD_IMG_FILE = null;
    private static File NEW_IMG_FILE = null;
    /**
     * Image_ParentFileName = xxx.assets
     */
    private static String Image_ParentFileName = null;

    public static void main(String[] args) throws Exception {
        File formal = new File(PROJECT_PATH + MD_PATH);
        File bate = new File(PROJECT_PATH + PROJECT_NAME + TEST_MD_PATH);
        log.info("bate-Path = {}", formal.toString());
        file(formal);
    }

    /**
     * 遍历 file 目录
     *
     * @param file 需要处理的目录[E:\java高级项目学习资料\note-jf\imgfileserve\md]
     * @throws Exception
     */
    public static void file(File file) throws Exception {
        //处理当前目录下的文件
        if (file.isFile()) {
            File old_File = null;
            //是png、jpg文件
            if (isPNG_JPG(file)) {
                //true开头的图片不需要处理
                if (!file.getName().toLowerCase().startsWith(PREFIX_TRUE)) {
                    log.info("--- file = {}", file);
                    old_File = file;
                    //把所有还没有处理的图片文件重命名为 false- 开头
                    file = upImageNameToFalse(file);
                    //处理false开头的图片
                    if (file.getName().toLowerCase().startsWith(PREFIX_FALSE)) {
                        //1、压缩图片，并替换原来文件
                        imgCompression(file);
                        //2、将压缩的img文件重命名为true-开头
                        file = upImageNameToTrue(file);
                        OLD_IMG_FILE = old_File;
                        NEW_IMG_FILE = file;
                        upMdFIle();
                    }
                }
            }
        }

        //是目录,继续遍历
        if (file.isDirectory()) {
            //列出当前：目录或文件
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (!".vuepress".equals(f.getName())) {
                    file(f);
                }
            }
        }

    }

    /**
     * 根据当前处理的图片进行更新md文件内容
     */
    private static void upMdFIle() throws IOException {
        // Image_ParentFileName = xxx.assets
        Image_ParentFileName = NEW_IMG_FILE.getParentFile().getName();
        // 如果png的上级目录不是以 assets 结尾时，说明当前png与md文件是同级目录
        if (Image_ParentFileName.endsWith(SUFFIX_ASSETS)) {
            // 根据当前png的上级目录名称来确定具体的md文件
            // mq-rocket.assets 的md文件为 mq-rocket.md
            String mdName = Image_ParentFileName.replace(PREFIX + SUFFIX_ASSETS, "") + PREFIX + SUFFIX_MD;
            // 拼接 xxx\note-jf\imgfileserve\md + \{mdName}
            String path = NEW_IMG_FILE.getParentFile().getParent() + SPLICING + mdName;
            File mdFile = new File(path);
            if (mdFile.exists() && isMD(mdFile)) {
                setMD(mdFile, false);
            }

        } else {
            // 拼接 xxx\note-jf\imgfileserve\java\java.md
            String path = NEW_IMG_FILE.getParentFile() + SPLICING + Image_ParentFileName + PREFIX + SUFFIX_MD;
            File mdFile = new File(path);
            if (mdFile.exists() && isMD(mdFile)) {
                setMD(mdFile, true);
            }
        }
    }

    /**
     * 传入md文件
     * <p>
     * init:E:\java高级项目学习资料\md-notes\idea操作.md
     *
     * @param mdFile
     */
    private static void setMD(File mdFile, boolean sameLevel) throws IOException {
        if (mdFile.isFile()) {
            log.info("--- 处理mdFile\t{}\n", mdFile);
            //image-20220119195308426.png
            String oldName = OLD_IMG_FILE.getName();
            //true-image-20220119195308426.png
            String newName = NEW_IMG_FILE.getName();

            //把md名称[Docker使用笔记.md]与新图片父级目录[Docker使用笔记.assets]比较，ok替换
            String mdFileName = mdFile.getName().replaceAll(PREFIX + SUFFIX_MD, "");
            String imageParentName = Image_ParentFileName.replaceAll(PREFIX + SUFFIX_ASSETS, "");
            if (mdFileName.equals(imageParentName)) {
                //读取 md 文件内容
                String mdContent = readFileConentToMd(mdFile);
                String oldImgName = "";
                String oldImgNametoFlase = "";
                String newImgName = "";
                if (sameLevel) {
                    oldImgName = oldName;
                    oldImgNametoFlase = PREFIX_FALSE + oldName;
                    newImgName = newName;
                } else {
                    oldImgName = Image_ParentFileName + "/" + oldName;
                    oldImgNametoFlase = Image_ParentFileName + "/" + PREFIX_FALSE + oldName;
                    newImgName = Image_ParentFileName + "/" + newName;
                }

                //img跟md是非同级：idea操作.assets/image-20220119195308426.png
                //img跟md是同级：image-20220119195308426.png
                if (mdContent.contains(oldImgName) || mdContent.contains(oldImgNametoFlase)) {
                    mdContent = mdContent.replaceAll(oldImgName, newImgName)
                            .replaceAll(oldImgNametoFlase, newImgName);
                    OutputStreamWriter fileOutputStream = null;
                    try {
                        fileOutputStream = new OutputStreamWriter(new FileOutputStream(mdFile), StandardCharsets.UTF_8);
                        fileOutputStream.append(mdContent);
                        fileOutputStream.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        fileOutputStream.close();
                    }
                }
            }
        }
    }

    /**
     * 是md文件
     *
     * @param mdFile
     * @return
     */
    private static boolean isMD(File mdFile) {
        String mdType = mdFile.getName().substring(mdFile.getName().lastIndexOf(PREFIX)).replaceAll("\\.", "");
        return SUFFIX_MD.equals(mdType);
    }

    /**
     * 读取md文件内容
     *
     * @param file
     * @return
     */
    private static String readFileConentToMd(File file) {
        //FileReader\FileInputStream
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] all = new byte[(int) file.length()];
            inputStream.read(all);
            inputStream.close();
            return new String(all, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("ImageUtil.readFileConentToMd:\t读取md文件内容异常\t{}\n", e.getMessage());
        }
        return null;
    }

    /**
     * 图片体积压缩
     * <p>
     * TYPE_USHORT_555_RGB:背景却是不透明的,可以设置为背景透明
     *
     * @param file
     * @return
     */
    private static void imgCompression(File file) throws Exception {
        Image image = ImageIO.read(file);
        int[] results = getImgWidthHeight(file);
        int width = results[0];
        int height = results[1];
        String imageType = getImageType(file);
        String outputImgPath = file.getPath();

        //imageType就是对应着Java内不的同格式的压缩方法:1-13
        // TYPE_INT_RGB:24位，压缩比例小（130kb->94kb）
        // TYPE_INT_ARGB_PRE：32位，压缩比例中（130kb->96kb）效果跟原图无异
        // TYPE_USHORT_555_RGB:24位，压缩比例中（130kb->45.9kb）
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB);
        bufferedImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        //创建文件输出流
        FileOutputStream outputStream = new FileOutputStream(outputImgPath);
        //转换编码格式JPEG，保存到out中
        ImageIO.write(bufferedImage, imageType.equals(SUFFIX_JPG) ? SUFFIX_JPEG : SUFFIX_PNG, outputStream);
        //关闭文件输出流
        outputStream.close();
    }

    /**
     * 判断文件后缀：png，jpg，jpeg
     *
     * @param file
     * @return boolean
     */
    private static boolean isPNG_JPG(File file) {
        return file.getName().toLowerCase().endsWith(SUFFIX_PNG) || file.getName().toLowerCase().endsWith(SUFFIX_JPG) || file.getName().toLowerCase().endsWith(SUFFIX_JPEG);
    }

    /**
     * 获取 image 类型：png、jpg
     *
     * @param file
     * @return
     */
    private static String getImageType(File file) {
        return file.getName().substring(file.getName().lastIndexOf(PREFIX)).replaceAll("\\.", "");
    }


    /**
     * 把所有还没有处理的图片文件名称设置为：false-xxx.png|false-xxx.jpg
     * <p>
     * 如果以[true-\false-]开头则跳过
     *
     * @param file
     */
    private static File upImageNameToFalse(File file) {
        //图片名称没有true-&&false-的文件
        if (!file.getName().contains(PREFIX_TRUE) && !file.getName().contains(PREFIX_FALSE)) {
            String thisFileParentPath = file.getParent();
            File newFileName = new File(thisFileParentPath + SPLICING + PREFIX_FALSE + file.getName());
            if (file.renameTo(newFileName)) {
                return newFileName;
            }
        }
        return file;
    }

    /**
     * 把false-修改为true-
     *
     * @param file
     */
    private static File upImageNameToTrue(File file) {
        //false 开头的文件
        if (file.getName().startsWith(PREFIX_FALSE) || file.getName().contains(PREFIX_FALSE)) {
            //当前目录：xxx.assets
            String newFileName = file.getName().replace(PREFIX_FALSE, PREFIX_TRUE);
            String thisFileParentPath = file.getParent();
            File newFile = new File(thisFileParentPath + SPLICING + newFileName);
            if (file.renameTo(newFile)) {
                return newFile;
            }
        }
        return file;
    }

    /**
     * 获取 File 图片宽度、高度
     *
     * @param file 图片路径
     * @return 返回图片的宽度
     */
    public static int[] getImgWidthHeight(File file) {
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        int[] result = {0, 0};
        try {
            // 获得文件输入流
            inputStream = new FileInputStream(file);
            // 从流里将图片写入缓冲图片区
            bufferedImage = ImageIO.read(inputStream);
            // 得到源图片宽
            result[0] = bufferedImage.getWidth(null);
            // 得到源图片高
            result[1] = bufferedImage.getHeight(null);
            inputStream.close();  //关闭输入流
        } catch (Exception exception) {
            log.error("getImgWidthHeight 获取图片宽度高度异常：{}", exception.getMessage());
        }

        return result;
    }

    /**
     * 获取 Image 图片宽度、高度
     *
     * @param image
     * @return
     */
    public static int[] getImgWidthHeight(Image image) {
        int[] result = {0, 0};
        try {
            // 得到源图片宽
            result[0] = image.getWidth(null);
            // 得到源图片高
            result[1] = image.getHeight(null);
        } catch (Exception exception) {
            log.error("ImageUtil.getImgWidthHeight-获取图片宽度高度-:\t{}\n", exception.getMessage());
        }
        return result;
    }
}
