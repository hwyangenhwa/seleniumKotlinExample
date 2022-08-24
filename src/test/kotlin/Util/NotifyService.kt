package Util

import org.junit.Test.None

object NotifyService {

    val notifyUrl: String = "http://www.naver.com"

    fun errorNoity(msg: String, errorinf: String){
        println("Error Msg : ${msg}, ${errorinf}")
    }
}