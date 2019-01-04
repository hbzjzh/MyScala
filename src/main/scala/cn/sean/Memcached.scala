package cn.sean

import java.net.InetSocketAddress
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import net.rubyeye.xmemcached.XMemcachedClient

/**
  * Created by sean on 2017/9/25.
  */
object Memcached extends Application {
  var mc_ip:String = "182.245.29.123"
  var mc_port:Int = 11212

  var ia = new InetSocketAddress(mc_ip, mc_port)
  var mc = new XMemcachedClient(ia)

  var activityID:Int = 3
  // 活动
  ActivityStatus(activityID)
  //

  def ActivityStatus(activityID:Int):Unit={
    var dayId:String = getTodayId
//    var PA:String = getValueByKey("PA-"+activityID)
//    if(PA==null) println("ID="+activityID+"的活动不存在")
//    else println("ID="+activityID+"的活动配置 "+PA)
    var PADS:String = getValueByKey("PADS-"+activityID+"-"+dayId)
    if(PADS==null) println("ID="+activityID+"的奖品池正在开放")
    else println("ID="+activityID+"的奖品池已经关闭")
  }

  def getYesterdayId:String={
    var cal = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    var dayId:String = new SimpleDateFormat("yyyyMMdd").format(cal.getTime)
    dayId
  }

  def getTodayId:String={
    var dayId:String = new SimpleDateFormat("yyyyMMdd").format(new Date())
    dayId
  }

  def setUserIdIntoMC:Unit={
    var userIds = Array(
      "0010039901049000153038FACA6359D1")
    var userId:String = null
    for(userId <- userIds) {
      var result:Boolean = mc.set("PRIZE"+userId, 0 , "-1")
      println("set "+userId+" into mc succeed!")
    }
  }

  def getValueByKey(key:String):String={
    var value = mc.get(key).toString
    value
  }

  def setAdGqInfo:Unit={
    var ad:String = getValueByKey("")
    var userIds = Array("")
    var userId:String = null
    for(userId <- userIds) {
      var result:Boolean = mc.set(userId, 0 , ad)
      println("set "+userId+" into mc succeed!")
    }
  }

}
