package org.wit.archaeologicalfieldwork.views.hillfortlist

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.archaeologicalfieldwork.models.HillfortModel
import org.wit.archaeologicalfieldwork.views.BasePresenter
import org.wit.archaeologicalfieldwork.views.BaseView
import org.wit.archaeologicalfieldwork.views.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddHillfort() {
    view?.navigateTo(VIEW.HILLFORT)
  }

  fun doEditHillfort(hillfort: HillfortModel) {
    view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
  }

  fun doShowHillfortsMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun loadHillforts() {
    doAsync {
      val hillforts = app.hillforts.findAll()
      uiThread {
        view?.showHillforts(hillforts)
      }
    }
  }

}