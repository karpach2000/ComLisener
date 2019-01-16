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
    print("Enter baude")
    baude = Integer.parseInt(readLine())
    println("Port list:")
    for(i in 0 until portNames.size)
        println("$i) ${portNames[i]}")
    println("Print host port number")
    var portNuber = Integer.parseInt(readLine())
    var portMaster = SerialPort(portNames[portNuber])
    portMaster.openPort()
    portMaster.setParams(baude, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE)
    portMaster.setEventsMask(SerialPort.MASK_RXCHAR)

    println("Print client port number")
    portNuber = Integer.parseInt(readLine())
    var portSlave = SerialPort(portNames[portNuber])
    portSlave.openPort()
    portSlave.setParams(baude, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE)
    portSlave.setEventsMask(SerialPort.MASK_RXCHAR)


    while (true)
    {
        if(portMaster.inputBufferBytesCount>0) {
            Thread.sleep(1)
            var data = portMaster.readIntArray()
            if(data!=null) {
            println("Master " + bytesToString(data))
            portSlave.writeIntArray(data)}
            else
                println("Master: null")
        }
        else if(portSlave.inputBufferBytesCount>0) {
            Thread.sleep(1)
            var data = portSlave.readIntArray()
            if(data!=null) {
                println("Slave " + bytesToString(data))
                portMaster.writeIntArray(data)
            }
            else
                println("Slave: null")

        }
        Thread.sleep(1)
    }



}


fun bytesToString(data: IntArray) : String
{

    var ans = ""
    for(i in data)
        ans = ans+" " + i.toString(16)
    return ans

}
