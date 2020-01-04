package org.wit.archaeologicalfieldwork.views

import android.content.Intent

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.AnkoLogger

import org.wit.archaeologicalfieldwork.models.HillfortModel
import org.wit.archaeologicalfieldwork.views.editlocation.EditLocationView
import org.wit.archaeologicalfieldwork.views.map.HillfortMapView
import org.wit.archaeologicalfieldwork.views.hillfort.HillfortView
import org.wit.archaeologicalfieldwork.views.hillfortlist.HillfortListView

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
  LOCATION, HILLFORT, MAPS, LIST
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

  var basePresenter: BasePresenter? = null

  fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
    var intent = Intent(this, HillfortListView::class.java)
    when (view) {
      VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
      VIEW.HILLFORT -> intent = Intent(this, HillfortView::class.java)
      VIEW.MAPS -> intent = Intent(this, HillfortMapView::class.java)
      VIEW.LIST -> intent = Intent(this, HillfortListView::class.java)
    }
    if (key != "") {
      intent.putExtra(key, value)
    }
    startActivityForResult(intent, code)
  }

  fun initPresenter(presenter: BasePresenter): BasePresenter {
    basePresenter = presenter
    return presenter
  }

  fun init(toolbar: Toolbar, upEnabled: Boolean) {
    toolbar.title = title
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
  }

  override fun onDestroy() {
    basePresenter?.onDestroy()
    super.onDestroy()
  }




  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      basePresenter?.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  open fun showHillfort(hillfort: HillfortModel) {}
  open fun showHillforts(hillforts: List<HillfortModel>) {}
  open fun showLocation(latitude : Double, longitude : Double) {}
  open fun showProgress() {}
  open fun hideProgress() {}
}