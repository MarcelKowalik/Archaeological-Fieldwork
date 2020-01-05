
package org.wit.archaeologicalfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archaeologicalfieldwork.models.mem.HillfortMemStore
import org.wit.archaeologicalfieldwork.models.HillfortStore
import org.wit.archaeologicalfieldwork.models.firebase.HillfortFireStore
import org.wit.archaeologicalfieldwork.room.HillfortStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore

  override fun onCreate() {
    super.onCreate()
    //hillforts = HillfortJSONStore(applicationContext)
    //hillforts = HillfortMemStore()
    //hillforts = HillfortStoreRoom(applicationContext)
    hillforts = HillfortFireStore(applicationContext)
    info("Hillforts started")
  }
}