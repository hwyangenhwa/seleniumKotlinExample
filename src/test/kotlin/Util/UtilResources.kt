package Util

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

object UtilResources {

    private var propertites: Properties? = null

    fun loadProperties(): Unit{
        try{
            propertites = Properties()
            propertites?.load(FileInputStream("config.properties"))
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun getProperties(properties: String): String{
        loadProperties()
        return propertites?.getProperty(properties).toString()
    }
}