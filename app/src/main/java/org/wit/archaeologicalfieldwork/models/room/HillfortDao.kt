package org.wit.archaeologicalfieldwork.room

import androidx.room.*
import org.wit.archaeologicalfieldwork.models.HillfortModel

@Dao
interface HillfortDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun create(hillfort: HillfortModel)

  @Query("SELECT * FROM HillfortModel")
  fun findAll(): List<HillfortModel>

  @Query("select * from HillfortModel where id = :id")
  fun findById(id: Long): HillfortModel

  @Update
  fun update(hillfort: HillfortModel)

  @Delete
  fun deleteHillfort(hillfort: HillfortModel)
}