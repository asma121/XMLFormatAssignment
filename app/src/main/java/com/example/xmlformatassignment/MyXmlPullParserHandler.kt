package com.example.xmlformatassignment

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val students = ArrayList<student>()
    private var text: String? = null

    private var studentName = ""
    private var mark =""
    private var id =0

    fun parse(inputStream: InputStream): List<student> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> {
                            studentName = text.toString()
                        }
                        tagName.equals("marks", ignoreCase = true) -> {
                            mark = text!!
                        }
                        tagName.equals("id", ignoreCase = true) -> {
                            id = text!!.toInt()
                        }
                        else -> students.add(student(id ,studentName, mark))
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return students
    }
}