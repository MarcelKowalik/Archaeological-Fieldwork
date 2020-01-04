package org.wit.archaeologicalfieldwork.helpers

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationRequest

val REQUEST_PERMISSIONS_REQUEST_CODE = 34

fun checkLocationPermissions(activity: Activity) : Boolean {
  if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
    return true
  }
  else {
    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_REQUEST_CODE)
    return false
  }
}

fun isPermissionGranted(code: Int, grantResults: IntArray): Boolean {
  var permissionGranted = false;
  if (code == REQUEST_PERMISSIONS_REQUEST_CODE) {
    when {
      grantResults.isEmpty() -> Log.i("Location", "User interaction was cancelled.")
      (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> {
        permissionGranted = true
        Log.i("Location", "Permission Granted.")
      }
      else -> Log.i("Location", "Permission Denied.")
    }
  }
  return permissionGranted
}

@SuppressLint("RestrictedApi")
fun createDefaultLocationRequest() : LocationRequest {
  val locationRequest = LocationRequest().apply {
    interval = 10000
    fastestInterval = 5000
    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
  }
  return locationRequest
}