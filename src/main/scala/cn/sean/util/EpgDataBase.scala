package cn.sean.util

import java.sql.{Connection, DriverManager}

import com.alibaba.fastjson.{JSONArray, JSONObject}

/**
  * Created by sean on 2017/7/21.
  */
class EpgDataBase(URL: String, USER: String, PASSWORD: String) {

  var connection: Connection = _

  def createConnection() : Connection = {
    println("create a new connection to " + URL)
    classOf[com.mysql.jdbc.Driver]
    connection = DriverManager.getConnection(URL, USER, PASSWORD)
    connection
  }

  def closeConnection : Unit = {
    println("close the connection to " + URL)
    connection.close()
  }

  def get(sql : String):JSONArray = {
    // 参数检查
    require(connection != null, "connection can't be null")
    require(sql != null, "sql can't be null")
    // 执行sql
    var ja = new JSONArray()
    try {
      val ps = connection.prepareStatement(sql)
      val rs = ps.executeQuery()
      // 读取数据
      while (rs.next()) {
        var jo = new JSONObject()
        for (i <- 1 to rs.getMetaData.getColumnCount) {
          //println(rs.getMetaData.getColumnName(i))
          jo.put(rs.getMetaData.getColumnName(i),rs.getString(rs.getMetaData.getColumnName(i)))
        }
        ja.add(jo)
      }
      // 关闭
      ps.close()
      rs.close()
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    } finally {
    }
    return ja
  }

  def set(sql : String):Int = {
    // 参数检查
    require(connection != null, "connection can't be null")
    require(sql != null, "sql can't be null")
    // 执行sql
    var ac = 0
    try {
      val ps = connection.prepareStatement(sql)
      ac = ps.executeUpdate()
      // 关闭
      ps.close()
    } catch {
      case ex: Exception => {
        ex.printStackTrace()
      }
    } finally {
    }
    return ac
  }

}

object EpgDataBase {

  val formalDB_URL = "jdbc:mysql://182.245.29.124:3306/Data?useUnicode=true&characterEncoding=UTF-8"
  val formalDB_USER = "paput"
  val formalDB_PASSWORD = "paput@2016iptv"
  val testDB_URL = "jdbc:mysql://222.221.10.102:3306/Data?useUnicode=true&characterEncoding=UTF-8"
  val testDB_USER = "root"
  val testDB_PASSWORD = "paput@2016iptv"
  val localDB_URL = "jdbc:mysql://127.0.0.1:3306/Data?useUnicode=true&characterEncoding=UTF-8"
  val localDB_USER = "root"
  val localDB_PASSWORD = "root"

  var formalEpgDataBase : EpgDataBase = _
  var testEpgDataBase : EpgDataBase = _
  var localEpgDataBase : EpgDataBase = _

  def apply(connType:String):EpgDataBase = {
    connType match {
      case "formal" => getFormalDB
      case "test" => getTestDB
      case "local" => getLocalDB
    }
  }

  def getFormalDB():EpgDataBase = {
    if(formalEpgDataBase==null) {
      formalEpgDataBase=new EpgDataBase(formalDB_URL,formalDB_USER,formalDB_PASSWORD)
      formalEpgDataBase.createConnection()
    }
    formalEpgDataBase
  }

  def getTestDB:EpgDataBase = {
    if(testEpgDataBase==null) {
      testEpgDataBase=new EpgDataBase(testDB_URL,testDB_USER,testDB_PASSWORD)
      testEpgDataBase.createConnection()
    }
    testEpgDataBase
  }

  def getLocalDB:EpgDataBase = {
    if(localEpgDataBase==null) {
      localEpgDataBase=new EpgDataBase(localDB_URL,localDB_USER,localDB_PASSWORD)
      localEpgDataBase.createConnection()
    }
    localEpgDataBase
  }

}