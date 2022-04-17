package com.raywenderlich.placebook.adapter
import android.app.Activity
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.raywenderlich.placebook.databinding.ContentBookmarkInfoBinding
import ui.MapsActivity

// 2
class BookmarkInfoWindowAdapter(private val context: Activity) :
    GoogleMap.InfoWindowAdapter {
    // 3
    private val binding =
        ContentBookmarkInfoBinding.inflate(context.layoutInflater)
    // 4
    override fun getInfoWindow(marker: Marker): View? {
// This function is required, but can return null if
// not replacing the entire info window
        return null
    }
    override fun getInfoContents(marker: Marker): View? {
        val imageView = null
        val bookMarkview = null
        val imageView =
        imageView.setImageBitmap(bookMarkview.getImage(context))
        binding.title.text = marker.title ?: ""
        binding.phone.text = marker.snippet ?: ""
        val imageView = binding.photo

        imageView.setImageBitmap((marker.tag as
                MapsActivity.PlaceInfo).image)
        return binding.root
    }
}