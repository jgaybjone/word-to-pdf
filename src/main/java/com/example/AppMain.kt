package com.example

import cn.afterturn.easypoi.entity.ImageEntity
import cn.afterturn.easypoi.word.WordExportUtil
import org.apache.poi.util.Units
import org.apache.poi.wp.usermodel.HeaderFooterType
import org.apache.poi.xwpf.usermodel.Document
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Classname AppMain
 * Description
 * Date 2019-07-10 13:59
 * Created by Wang jun gang
 */

fun main() {
    val a = "大家好，我是abcd"
    println(a.length)
    val map = HashMap<String, Any>()
    map["username"] = "张三"
    map["age"] = 12
    val list = ArrayList<Map<String, Any>>()
    list.add(mapOf("id" to 1, "count" to 20))
    list.add(mapOf("id" to 2, "count" to 40))
    list.add(mapOf("id" to 3, "count" to 60))
    list.add(mapOf("id" to 4, "count" to 80))
    map["items"] = list

    map["bar_code"] = ImageEntity("/Users/mac/Desktop/s1.jpg", 30, 30)

    val doc = WordExportUtil.exportWord07("/Users/mac/Desktop/word_excel.docx", map)


    val footer = doc.createFooter(HeaderFooterType.DEFAULT)

    val paragraph = footer.createParagraph()
    val run = paragraph.createRun()
    paragraph.alignment = ParagraphAlignment.RIGHT
    run.addPicture(FileInputStream("/Users/mac/Desktop/s1.jpg"), Document.PICTURE_TYPE_JPEG, "samplePict.jpeg", Units.toEMU(100.0), Units.toEMU(50.0))
    val oFile = FileOutputStream("/Users/mac/Desktop/word_excel_done.docx")


    doc.write(oFile)
    oFile.close()
    doc.close()


}