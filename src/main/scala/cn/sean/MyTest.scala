package cn.sean

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

/**
  * Created by sean on 2017/7/21.
  */
object MyTest extends App {

  // select * from EPG_LOG_20170816 where action_type='vod_playing' and duration>3600 and id>2000000 order by id asc limit 1

//  val str: String = "{\"stb_device_id\":\"6351607\",\"duration\":\"13657\",\"action_type\":\"tv_playing\",\"end_time\":\"20170726002431\",\"media_id\":\"108\",\"start_time\":\"20170725203654\"}"
//  val json = JSON.parseFull(str)
//  val map: Map[String, Any] = json match {
//    case Some(m: Map[String, Any]) => m
//    case None => Map()
//  }
//  val startTime: String = map.get("start_time") match {
//    case Some(s: String) => s
//    case None => ""
//  }
  val sdf = new SimpleDateFormat("yyyyMMddHHmmss")
  val sd:Date = sdf.parse("20170725203654")
  val ed:Date = sdf.parse("20170726002431")
  calDuration(sd,ed)
  //println(calDurationInHour(date))

  def calDayId(sd:Date):String = {
    new SimpleDateFormat("yyyyMMdd").format(sd)
  }

  def calHour(sd:Date):String = {
    new SimpleDateFormat("HH").format(sd)
  }

  var cal: Calendar = _
  def calDuration(sd:Date,ed:Date):Unit = {
    print("dayId:"+calDayId(sd)+",hour:"+calHour(sd))
    cal = Calendar.getInstance()
    cal.setTime(sd)
    if(ed.getTime<=sd.getTime)
      println(",second:"+0)
    else {
      val sjc : Long = (ed.getTime-sd.getTime)/1000
      if(sjc>=3600) {
        cal.add(Calendar.HOUR_OF_DAY,1)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.SECOND,0)
        println(",second:"+(cal.getTimeInMillis-sd.getTime)/1000)
        calDuration(cal.getTime, ed)
      } else {
        println(",second:" + (ed.getTime - sd.getTime) / 1000)
      }
    }
  }


}

