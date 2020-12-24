package com.liu.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.word.Word07Writer;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @program: JVMDome
 * @description: 测试
 * @author: liuwenhao
 * @create: 2020-11-23 11:51
 **/
public class ExcelTest {
    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();
        File file = new File("C:\\Users\\浩\\Desktop\\创智港人才活动计划表1120.xlsx");
        ExcelReader reader = ExcelUtil.getReader(file);
        List<Map<String, Object>> maps = reader.readAll();
        maps.forEach(stringObjectMap -> {
            Word07Writer writer = new Word07Writer();
            writer.addText(ParagraphAlignment.CENTER,new Font("宋体", Font.BOLD, 20), stringObjectMap.get("活动名称").toString());
            stringObjectMap.forEach((s, o) -> {
                if (!"序号".equals(s)){
                    writer.addText(new Font("宋体", Font.PLAIN, 14), s, ":" , o.toString());
                }
            });
            writer.flush(FileUtil.file(desktopPath + "\\创智港\\" + stringObjectMap.get("活动名称").toString() + ".docx"));
            writer.close();
        });
        reader.close();
    }
}