package cn.sean.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.alibaba.fastjson.{JSONArray, JSONObject}

/**
  * Created by sean on 2017/7/31.
  */
class EpgUtils {

  def calDayId(sd:Date):String = {
    new SimpleDateFormat("yyyyMMdd").format(sd)
  }

  def calHour(sd:Date):String = {
    new SimpleDateFormat("HH").format(sd)
  }

  var jaOutput:JSONArray = new JSONArray()
  var joOutput:JSONObject = _
  var cal:Calendar = _
  def splitDuration(sd:Date,ed:Date):JSONArray = {
    joOutput = new JSONObject();
    joOutput.put("dayId",calDayId(sd))
    joOutput.put("hour",calHour(sd))
    cal = Calendar.getInstance()
    cal.setTime(sd)
    if(ed.getTime<=sd.getTime) {
      joOutput.put("second", "0")
      jaOutput.add(joOutput)
    } else {
      val sjc : Long = (ed.getTime-sd.getTime)/1000
      if(sjc>=3600) {
        cal.add(Calendar.HOUR_OF_DAY,1)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.SECOND,0)
        joOutput.put("second", ((cal.getTimeInMillis-sd.getTime)/1000).toString)
        jaOutput.add(joOutput)
        splitDuration(cal.getTime, ed)
      } else {
        joOutput.put("second", ((ed.getTime-sd.getTime)/1000).toString)
        jaOutput.add(joOutput)
      }
    }
   jaOutput
  }

}
