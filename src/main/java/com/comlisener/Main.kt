package com.comlisener
import jssc.SerialPort
import jssc.SerialPortException
import jssc.SerialPortList

private var portMaster: SerialPort? = null//Последовательный порт
private var portSlave: SerialPort? = null//Последовательный порт
private var baude = 9600
fun main()
{
    val portNames = SerialPortList.getPortNames()
    println("Port list:")
    for(i in 0..portNames.size)
        println("$i) $portNames[i]")
    println("Print host port number")





}