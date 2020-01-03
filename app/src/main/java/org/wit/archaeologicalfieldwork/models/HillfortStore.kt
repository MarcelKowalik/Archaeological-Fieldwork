package org.wit.archaeologicalfieldwork.models

interface HillfortStore {
  fun findAll(): List<HillfortModel>
  fun create(hillfort: HillfortModel)
  fun update(hillfort: HillfortModel)
  fun delete(hillfort: HillfortModel)
}