package org.wit.archaeologicalfieldwork.views.hillfort

import android.content.Intent
import org.wit.archaeologicalfieldwork.helpers.showImagePicker
import org.wit.archaeologicalfieldwork.models.Location
import org.wit.archaeologicalfieldwork.models.HillfortModel
import org.wit.archaeologicalfieldwork.views.*

class HillfortPresenter(view: BaseView) : BasePresenter(view) {

    var hillfort = HillfortModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
    var edit = false;

    init {
        if (view.intent.hasExtra("hillfort_edit")) {
            edit = true
            hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
            view.showHillfort(hillfort)
        }
    }

    fun doAddOrSave(title: String, description: String) {
        hillfort.title = title
        hillfort.description = description
        if (edit) {
            app.hillforts.update(hillfort)
        } else {
            app.hillforts.create(hillfort)
        }
        view?.finish()
    }

    fun doCancel() {
        view?.finish()
    }

    fun doDelete() {
        app.hillforts.delete(hillfort)
        view?.finish()
    }

    fun doSelectImage() {
        view?.let {
            showImagePicker(view!!, IMAGE_REQUEST)
        }
    }

    fun doSetLocation() {
        if (edit == false) {
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", defaultLocation)
        } else {
            view?.navigateTo(
                VIEW.LOCATION,
                LOCATION_REQUEST,
                "location",
                Location(hillfort.lat, hillfort.lng, hillfort.zoom)
            )
        }
    }

    override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            IMAGE_REQUEST -> {
                hillfort.image = data.data.toString()
                view?.showHillfort(hillfort)
            }
            LOCATION_REQUEST -> {
                val location = data.extras?.getParcelable<Location>("location")!!
                hillfort.lat = location.lat
                hillfort.lng = location.lng
                hillfort.zoom = location.zoom
            }
        }
    }
}