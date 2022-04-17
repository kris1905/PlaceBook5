package viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import model.Bookmark
import repository.BookmarkRepo
import util.ImageUtils

// 1
class MapsViewModel(application: Application) :
    AndroidViewModel(application) {
    private val TAG = "MapsViewModel"
    // 2
    private val bookmarkRepo: BookmarkRepo = BookmarkRepo(
        getApplication())
    // 3
    private fun bookmarkToMarkerView(bookmark: Bookmark) =
        BookmarkMarkerView(
            bookmark.id,
            LatLng(bookmark.latitude, bookmark.longitude),
            bookmark.name,
            bookmark.phone
        )
    fun addBookmarkFromPlace(place: Place, image: Bitmap?) {
// 4
        val bookmark = bookmarkRepo.createBookmark()
        bookmark.placeId = place.id
        bookmark.name = place.name.toString()
        bookmark.longitude = place.latLng?.longitude ?: 0.0
        bookmark.latitude = place.latLng?.latitude ?: 0.0
        bookmark.phone = place.phoneNumber.toString()
        bookmark.address = place.address.toString()
// 5
        val newId = bookmarkRepo.addBookmark(bookmark)
        Log.i(TAG, "New bookmark $newId added to the database.")
        image?.let { bookmark.setImage(it, getApplication()) }
    }
    data class BookmarkMarkerView(
        var id: Long? = null,
        var location: LatLng = LatLng(0.0, 0.0),
        var name: String = "",
        var phone: String = ""
    ) {
        fun getImage(context: Context) = id?.let {
            ImageUtils.loadBitmapFromFile(context,
                Bookmark.generateImageFilename(it))
        }
    }
}