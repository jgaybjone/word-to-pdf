package com.example

import cn.afterturn.easypoi.entity.ImageEntity
import cn.afterturn.easypoi.word.WordExportUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.poi.util.Units
import org.apache.poi.wp.usermodel.HeaderFooterType
import org.apache.poi.xwpf.usermodel.Document
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.HashMap



/**
 * Classname AppMain
 * Description
 * Date 2019-07-10 13:59
 * Created by Wang jun gang
 */

fun main() {
//    val a = "大家好，我是abcd"
//    println(a.length)
//    val map = HashMap<String, Any>()
//    map["username"] = "张三"
//    map["age"] = 12
//    val list = ArrayList<Map<String, Any>>()
//    list.add(mapOf("id" to 1, "count" to 20))
//    list.add(mapOf("id" to 2, "count" to 40))
//    list.add(mapOf("id" to 3, "count" to 60))
//    list.add(mapOf("id" to 4, "count" to 80))
//    map["items"] = list
//
//    map["bar_code"] = ImageEntity("/Users/mac/Desktop/s1.jpg", 30, 30)

    val doc = WordExportUtil.exportWord07("/Users/jungangwang/Desktop/word_excel.docx", beanToMap())


    val footer = doc.createFooter(HeaderFooterType.DEFAULT)

    val paragraph = footer.createParagraph()
    val run = paragraph.createRun()
    paragraph.alignment = ParagraphAlignment.RIGHT
    run.addPicture(FileInputStream("/Users/jungangwang/Desktop/s1.jpg"), Document.PICTURE_TYPE_JPEG, "samplePict.jpeg", Units.toEMU(100.0), Units.toEMU(50.0))
    val oFile = FileOutputStream("/Users/jungangwang/Desktop/word_excel_done.docx")


    doc.write(oFile)
    oFile.close()
    doc.close()

}

fun beanToMap(): Map<String, *> {
    val objectMapper = ObjectMapper()
    val b1 = BB()
    b1.id = 1
    b1.count = 100
    val b2 = BB()
    b2.id = 2
    b2.count = 200
    val aa = AA()
    aa.username = "测试专用"
    aa.age = 22
    aa.items  = listOf(b1, b2)
    val json = objectMapper.writeValueAsString(aa)

    val type = objectMapper.typeFactory.constructParametricType(HashMap::class.java, String::class.java, Any::class.java)

    return objectMapper.readValue(json, type)

}

open class AA {
    var username: String? = null
    var age: Int? = null
    var items : List<BB>? = null
}

open class BB {
    var id: Int? = null
    var count: Int? = null
}