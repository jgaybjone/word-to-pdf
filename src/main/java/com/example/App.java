package com.example;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.xwpf.converter.core.utils.StringUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classname App
 * Description
 * Date 2019-07-10 13:41
 * Created by Wang jun gang
 */
public class App {


    public static void main(String[] args) throws Exception {
        String filepath = "/Users/mac/Downloads/pageword.docx";
        String outpath = "/Users/mac/Downloads/pageword.pdf";

        InputStream source = new FileInputStream(filepath);
        OutputStream target = new FileOutputStream(outpath);
        Map<String, String> params = new HashMap<>();
        params.put("test", "Hello world!");


        PdfOptions options = PdfOptions.create();

        wordConverterToPdf(source, target, options, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source 源为word文档， 必须为docx文档
     * @param target 目标输出
     * @param params 需要替换的变量
     * @throws Exception
     */
    public static void wordConverterToPdf(InputStream source,
                                          OutputStream target, Map<String, String> params) throws Exception {
        wordConverterToPdf(source, target, null, params);
    }

    /**
     * 将word文档， 转换成pdf, 中间替换掉变量
     *
     * @param source  源为word文档， 必须为docx文档
     * @param target  目标输出
     * @param params  需要替换的变量
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他
     * @throws IOException 文件流io异常
     */
    public static void wordConverterToPdf(InputStream source, OutputStream target,
                                          PdfOptions options,
                                          Map<String, String> params) throws IOException {
        XWPFDocument doc = new XWPFDocument(source);
        paragraphReplace(doc.getParagraphs(), params);
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    paragraphReplace(cell.getParagraphs(), params);
                }
            }
        }
        PdfConverter.getInstance().convert(doc, target, options);
    }

    /**
     * 替换段落中内容
     */
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            for (XWPFParagraph p : paragraphs) {
                for (XWPFRun r : p.getRuns()) {
                    String content = r.getText(r.getTextPosition());
                    System.out.println(content);
                    if (StringUtils.isNotEmpty(content) && params.containsKey(content)) {
                        r.setText(params.get(content), 0);
                    }
                }
            }
        }
    }
}
