package com.cps007.imgfileserve;

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
public class ImageUtil {
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String prefix = ".";
    private final static String START_FALSE = "false-";
    private final static String START_TRUE = "true-";
    private final static String MD = "md";
    private final static String PNG = "png";
    private final static String JPG = "jpg";
    private final static String JPEG = "jpeg";
    private static File TEMP_FILE = null;
    private static File NEW_FILE = null;

    public static void main(String[] args) throws Exception {
        //获取当前md-notes项目的window路径 getCanonicalPath\\
        // targetFile=（E:\java高级项目学习资料\md-notes）
        File folder = new File(PROJECT_PATH);
        File test = new File(PROJECT_PATH + "\\imgfileserve\\src\\main\\java\\com\\cps007\\imgfileserve");
        file(folder);
    }

    private static void size(File folder) {
        if (folder.isDirectory()) {
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                if (f.isDirectory() && !f.getName().equals(".git") && !f.getName().equals(".idea")) {
                    System.out.println("this-文件夹：：：" + f.getName() + "::" + f.length());
                    if (!"imgfileserve".equals(folder.getName())) {
                        size(f);
                    }
                }
            }
        }
    }

    /**
     * 遍历目录
     */
    public static void file(File file) throws Exception {
        if (file.isFile()) {
            File tempFile = null;
            //是png、jpg文件
            if (getFileType(file)) {
                //true开头的图片不需要处理
                if (!file.getName().toLowerCase().startsWith(START_TRUE)) {
                    tempFile = file;
                    //把所有还没有处理的图片文件重命名为 false- 开头
                    file = upImageNameToFalse(file);
                    //处理false开头的图片
                    if (file.getName().toLowerCase().startsWith(START_FALSE)) {
                        //1、压缩
                        imgCompression(file);
                        //2、文件重命名为true-开头
                        file = upImageNameToTrue(file);
                        TEMP_FILE = tempFile;
                        NEW_FILE = file;
                        upMdFIle();
                    }
                }
            }
        }

        //是目录,继续遍历
        if (file.isDirectory()) {
            //保存当前目录的所有png、jpg文件
            for (File f : Objects.requireNonNull(file.listFiles())) {
                //排除 imgfileserve imgs 项目
                if (!"imgfileserve".equals(file.getName()) || !".vuepress".equals(file.getName())) {
                    file(f);
                }
            }
        }

    }

    /**
     * 根据当前处理的图片进行更新md文件内容
     */
    private static void upMdFIle() throws IOException {
        //根据当前png的上级命令名称来确定具体的md文件
        String mdName = NEW_FILE.getParentFile().getName().replace(".assets", "");
        //PROJECT_PATH = E:\java高级项目学习资料\md-notes
        String path = PROJECT_PATH;
//        String path = NEW_FILE.getParentFile().getParent();

        // E:\java高级项目学习资料\md-notes\idea操作.md
        File mdFile = new File(path + "\\" + mdName + ".md");
        System.out.printf("mdFile\t%s\n", mdFile);
        if (mdFile.exists() && isMD(mdFile)) {
            setMD(mdFile);
        }
    }

    /**
     * 传入md文件
     * <p>
     * init:E:\java高级项目学习资料\md-notes\idea操作.md
     *
     * @param mdFile
     */
    private static void setMD(File mdFile) throws IOException {
        if (mdFile.isFile()) {
            //Docker使用笔记.assets
            String parentFileName = NEW_FILE.getParentFile().getName();
            //image-20220119195308426.png
            String oldName = TEMP_FILE.getName();
            //true-image-20220119195308426.png
            String newName = NEW_FILE.getName();

            //把md名称[Docker使用笔记.md]与新图片父级目录[Docker使用笔记.assets]比较，ok替换
            String mdFileName = mdFile.getName().replaceAll(".md", "");
            String imageParentName = parentFileName.replaceAll(".assets", "");
            if (mdFileName.equals(imageParentName)) {
                //获取 Docker使用笔记.md
                String mdContent = readFileConentToMd(mdFile);
                String oldImgName = parentFileName + "/" + oldName;
                String oldImgNametoFlase = parentFileName + "/false-" + oldName;
                String newImgName = parentFileName + "/" + newName;
                //idea操作.assets/image-20220119195308426.png
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
        String mdType = mdFile.getName().substring(mdFile.getName().lastIndexOf(".")).replaceAll("\\.", "");
        return MD.equals(mdType);
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
            System.err.printf("ImageUtil.readFileConentToMd:\t读取md文件内容异常\t%s\n", e.getMessage());
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
        String imgPath = file.getPath();

        //imageType就是对应着Java内不的同格式的压缩方法:1-13
        // TYPE_INT_RGB:24位，压缩比例小（130kb->94kb）、
        // TYPE_INT_ARGB_PRE：32位，压缩比例中（130kb->96kb）效果跟原图无异、
        // TYPE_USHORT_555_RGB:24位，压缩比例中（130kb->45.9kb）、
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB);
        bufferedImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        //创建文件输出流
        FileOutputStream outputStream = new FileOutputStream(imgPath);
        //转换编码格式JPEG，保存到out中
        ImageIO.write(bufferedImage, imageType.equals(JPG) ? JPEG : PNG, outputStream);
        //关闭文件输出流
        outputStream.close();
    }

    /**
     * 获取 image 类型：png、jpg
     *
     * @param file
     * @return
     */
    private static String getImageType(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".")).replaceAll("\\.", "");
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
        if (!file.getName().contains(START_TRUE) && !file.getName().contains(START_FALSE)) {
            String thisFileParentPath = file.getParent();
            File newFileName = new File(thisFileParentPath + "\\" + START_FALSE + file.getName());
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
        if (file.getName().contains(START_FALSE)) {
            //当前目录：xxx.assets
            String newFileName = file.getName().replace("false-", "true-");
            String thisFileParentPath = file.getParent();
            File newFile = new File(thisFileParentPath + "\\" + newFileName);
            if (file.renameTo(newFile)) {
                return newFile;
            }
        }
        return file;
    }

    /**
     * 判断文件后缀：png，jpg
     *
     * @param file
     * @return boolean
     */
    private static boolean getFileType(File file) {
        return file.getName().toLowerCase().endsWith(PNG) || file.getName().toLowerCase().endsWith(JPG);
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
            System.out.printf("ImageUtil.getImgWidthHeight-File-:\t%s\n" + "获取图片宽度高度");
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
            System.out.printf("ImageUtil.getImgWidthHeight-Image-:\t%s\n" + "获取图片宽度高度");
        }
        return result;
    }
}
