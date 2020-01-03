//package org.wit.archaeologicalfieldwork.main
//
//import android.app.Application
//import org.jetbrains.anko.AnkoLogger
//import org.jetbrains.anko.info
//import org.wit.archaeologicalfieldwork.models.HillfortMemStore
//
//
//class MainApp : Application(), AnkoLogger {
//
//  val hillforts = HillfortMemStore()
//
//  override fun onCreate() {
//    super.onCreate()
//    info("Hilforts started")
//  }
//}


package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.HillfortJSONStore
import org.wit.archaeologicalfieldwork.models.HillfortMemStore
import org.wit.archaeologicalfieldwork.models.HillfortStore

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore

  override fun onCreate() {
    super.onCreate()
    //hillforts = HillfortJSONStore(applicationContext)
    hillforts = HillfortMemStore()
    info("Hillforts started")
  }
}