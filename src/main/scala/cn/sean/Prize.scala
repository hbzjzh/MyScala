package cn.sean

import java.text.SimpleDateFormat
import java.util.Calendar


/**
  * Created by sean on 2017/9/8.
  */
object Prize extends App {

  var list = List(
    Array("食用油", "2", "0", "2"),Array("食用油", "2", "11", "1"),Array("食用油", "2", "13", "1"),Array("食用油", "2", "15", "1"),Array("食用油", "2", "17", "1")
    ,Array("雨伞", "3", "0", "2"),Array("雨伞", "3", "11", "1"),Array("雨伞", "3", "13", "1"),Array("雨伞", "3", "15", "1"),Array("雨伞", "3", "17", "1")
    ,Array("卷筒纸", "7", "0", "2"),Array("卷筒纸", "7", "11", "1"),Array("卷筒纸", "7", "13", "1"),Array("卷筒纸", "7", "15", "1"),Array("卷筒纸", "7", "17", "1")
    ,Array("话费10元", "6", "0", "10"),Array("话费10元", "6", "11", "5"),Array("话费10元", "6", "13", "5"),Array("话费10元", "6", "15", "5"),Array("话费10元", "6", "17", "5")
    ,Array("话费5元", "5", "0", "30"),Array("话费5元", "5", "11", "15"),Array("话费5元", "5", "13", "15"),Array("话费5元", "5", "15", "15"),Array("话费5元", "5", "17", "15")
    ,Array("山泉水票", "4", "0", "30"),Array("山泉水票", "4", "11", "20"),Array("山泉水票", "4", "12", "20"),Array("山泉水票", "4", "13", "20"),Array("山泉水票", "4", "14", "20"),Array("山泉水票", "4", "15", "20"),Array("山泉水票", "4", "16", "20"),Array("山泉水票", "4", "17", "20"),Array("山泉水票", "4", "18", "20")
  )
  /*
  谢谢参与0 iPhone1 食用油2 雨伞3 水票4 话费5元5 话费10元6 卷纸7

  iPhone 8（全网通）1 18天总计1台 在国庆期间抽出
  食用油  40 18天总计40份 前十天，2份/天 后八天，5份/2天
  雨伞   40 18天总计40份 前十天，2份/天 后八天，5份/2天
  卷筒纸  40 18天总计40份 前十天，2份/天 后八天，5份/2天
  电信手机话费充值 200 18天话费总值2000元 前十天，10份/天 后八天，25份/2天
  电信手机话费充值 600 18天话费总值3000元 前十天，30份/天 后八天，75份/2天
  云南山泉水票 700 每份2张，18天水票总计700份 前十天，30份/天 后八天，50份/天
  */

    //var db = EpgDataBase("local")
    var idx:Int = 0
    var cal = Calendar.getInstance()
    cal.set(2017,9,21)
    var sdf = new SimpleDateFormat("yyyyMMdd")
    for(idx <- 0 until 18 ) {
      cal.add(Calendar.DATE,1)
      //println(sdf.format(cal.getTime))
      val it = Prize.list.iterator
      while (it.hasNext){
        val arr = it.next()
        if("0".equals(arr(2))) {
          println("('"+arr(0)+"',"+arr(1)+","+sdf.format(cal.getTime)+")")
        } else if(idx.toString.equals(arr(2))){
          println("('"+arr(0)+"',"+arr(1)+","+sdf.format(cal.getTime)+")")
        }
      }
    }

  var sb: StringBuilder = new StringBuilder()
  def setIntoSB:Unit={

  }

}