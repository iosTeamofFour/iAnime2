package Utils

import android.content.res.AssetManager
import android.net.Uri

fun AssetManager.AsUri(AssetFileName : String) = "file:///android_asset/$AssetFileName"