package kr.com.rlwhd.kotlinexample.network

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.UnknownHostException

class NetworkTest {
    val TAG = javaClass.simpleName

    fun lookUp() {
        try {
//            val address = InetAddress.getByName(args[0])
            val address = InetAddress.getAllByName("www.naver.com")
            for (i in address) {
                Log.d("11111111","Name: " + i.hostName)
                Log.d("11111111", "Addr: " + i.hostAddress)
                Log.d("11111111", "Canonical: " + i.canonicalHostName)
            }
        } catch (e: UnknownHostException) {
            Log.e(TAG, "InetAddress Error - $e")
        }

    }

    fun reachable() {
        val mBufferedReader = BufferedReader(InputStreamReader(System.`in`))
        println("Enter names and Ip addresses. Enter \"exit\" to quit.")
        try {
            while (true) {
                val host = mBufferedReader.readLine()
                if(host == "exit" || host == "quit") {
                    break
                }

                try {
                    val thisComputer = InetAddress.getByName(host)
                    println(thisComputer.hostAddress)
                    if (thisComputer.isReachable(6000)){
                        System.out.printf("%s is reachable \n", thisComputer.hostName)
                    } else {
                        System.out.printf("%s is unreachable \n", thisComputer.hostName)
                    }
                } catch (e: UnknownHostException){
                    Log.e(TAG, "thisComputer Error - $e")
                } catch (e: InterruptedException){
                    Log.e(TAG, "thisComputer Error - $e")
                }
            }

        } catch (e: IOException) {
            Log.e(TAG, "host Error - $e")
        }
    }
}