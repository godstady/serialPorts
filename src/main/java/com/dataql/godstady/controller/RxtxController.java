package com.dataql.godstady.controller;

import com.dataql.godstady.mapper.ZzwInfoMapper;
import com.dataql.godstady.rxtx.RXTXtest;
import gnu.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

@Controller
public class RxtxController {

    @Autowired
    private ZzwInfoMapper mapper;

    public final SerialPort serialPort = openSerialPort("COM3",115200);

    @RequestMapping("/sync1")
    @ResponseBody
    public String sync1(){

        RXTXtest test = new RXTXtest();
        test.sync();

        return "";
    }

    @RequestMapping("/openPort")
    @ResponseBody
    public String sync(){

        List<String> systemPorts = new ArrayList<String>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }
        System.out.println("系统可用端口列表："+systemPorts);

        //开启端口COM3，波特率9600  115200



        RxtxController.setListenerToSerialPort(serialPort, new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {//数据通知
                    byte[] bytes = RXTXtest.readData(serialPort);
                    System.out.println("收到的数据长度："+bytes.length);
                    String str  = new String(bytes);
                    String[] split = new String(bytes).split(",");
                    if (split!=null&&split.length>0){
                        String zngbh = mapper.getZngbh(split[0]);
                        System.out.println(zngbh);
                    }
                    System.out.println(str);

                }
            }
        });

        return "";
    }


    @RequestMapping("/closePort")
    @ResponseBody
    public String colseport(){

        RXTXtest.closeSerialPort(serialPort);


        return "";
    }


    public static SerialPort openSerialPort(String serialPortName,int baudRate) {
        try {
            //通过端口名称得到端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            //打开端口，（自定义名字，打开超时时间）
            CommPort commPort = portIdentifier.open(serialPortName, 2222);
            //判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8，停止位1，校验位无）
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                System.out.println("开启串口成功，串口名称："+serialPortName);
                return serialPort;
            }
            else {
                //是其他类型的端口
                throw new NoSuchPortException();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void setListenerToSerialPort(SerialPort serialPort, SerialPortEventListener listener) {
        try {
            //给串口添加事件监听
            serialPort.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);//串口有数据监听
        serialPort.notifyOnBreakInterrupt(true);//中断事件监听
    }


}
