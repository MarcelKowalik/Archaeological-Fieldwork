package org.wit.archaeologicalfieldwork.views.hillfort

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.archaeologicalfieldwork.R
import org.wit.archaeologicalfieldwork.helpers.readImageFromPath
import org.wit.archaeologicalfieldwork.models.HillfortModel
import org.wit.archaeologicalfieldwork.views.BaseView

class HillfortView : BaseView(), AnkoLogger {

    lateinit var presenter: HillfortPresenter
    var hillfort = HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        super.init(toolbarAdd, true)

        presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            presenter.doConfigureMap(it)
            it.setOnMapClickListener { presenter.doSetLocation() }
        }

        chooseImage.setOnClickListener { presenter.doSelectImage() }
    }

    override fun showHillfort(hillfort: HillfortModel) {
        hillfortTitle.setText(hillfort.title)
        description.setText(hillfort.description)
        hillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
        if (hillfort.image != null) {
            chooseImage.setText(R.string.change_hillfort_image)
        }
        lat.setText("%.6f".format(hillfort.lat))
        lng.setText("%.6f".format(hillfort.lng))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }
            R.id.item_save -> {
                if (hillfortTitle.text.toString().isEmpty()) {
                    toast(R.string.enter_hillfort_title)
                } else {
                    presenter.doAddOrSave(hillfortTitle.text.toString(), description.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        presenter.doCancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        presenter.doResartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}


